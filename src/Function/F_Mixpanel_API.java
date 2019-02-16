package Function;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utility.Constants;
import Utility.Utils;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class F_Mixpanel_API {

	/*
	 * Method name : callAPI Description : This method will set MP authentication,
	 * insert JQL script as a parameter of request and send post request Accept :
	 * String (API based Url, API secret 64base encoded, JQL script) Return :
	 * JSONArray (an array of MP events)
	 */
	public static JSONArray callAPI(String jqlScript, int expected_NumEvents) throws JSONException, InterruptedException {
		RestAssured.baseURI = Utils.readXmlNodeByTagname(Constants.xml_File_Path, Constants.xml_api_Url_Tagname);
		RequestSpecification request = RestAssured.given().header(new Header("Authorization", "Basic "
				+ Utils.readXmlNodeByTagname(Constants.xml_File_Path, Constants.xml_api_Secret_Encoded_Tagname)));
		request.params("script", jqlScript);
		Response response = request.post();
		JSONArray events = new JSONArray(response.getBody().asString());
		for (int i = 0; i < 30; i++)
		{
			if (events.length() == expected_NumEvents)
			{
				break;
			}
			else if (i == 29)
			{
				System.out.println("Missing or redudant events!");
			}
			else
			{
				Thread.sleep(5000);
				response = request.post();
				events = new JSONArray(response.getBody().asString());
			}
		}
		return events;
	}

	/*
	 * Method name : replaceJQLParams Description : This method will read JQL script
	 * placing in ProjectName/src/JQL ,replace some hard-coded values in JQL script
	 * Accept : String (user email, fromDate, toDate) Return : String (Script with
	 * new parameters)
	 */
	public static String replaceJQLParams(String eventName, String userEmail) throws IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateAsString = simpleDateFormat.format(new Date());
		String script = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/JQL")));
		return script.replace("eventName", eventName)
					 .replace("testemail@venngage.com", userEmail)
					 .replace("from_today", dateAsString)
					 .replace("to_today", dateAsString);
	}

	/*
	 * Method name : verifyEventsGenerated Description : This method will compare 2
	 * json arrays Accept : JSONArray (actual events, expected events) Return :
	 * boolean
	 */
	public static boolean verifyEventsGenerated(JSONArray actualEvents, JSONArray expectedEvents) throws JSONException {
		boolean result = false;
		for (int j = 0; j < actualEvents.length(); j++) {
			result = result && verifyEvent(actualEvents.getJSONObject(j), expectedEvents.getJSONObject(j));
		}
		return result;
	}

	/*
	 * Method name : getEventByName Description : This method will look for an event
	 * from array Accept : JSONArray (events), String (eventName) Return :
	 * JSONObject
	 */
	public static JSONObject getEventByName(JSONArray events, String eventName) throws JSONException {
		JSONObject expectedEvent = null;
		for (int i = 0; i < events.length(); i++)
			if (events.getJSONObject(i).getString("name").equalsIgnoreCase(eventName)) {
				expectedEvent = events.getJSONObject(i);
				break;
			}
		return expectedEvent;
	}
	
	/*
	 * Method name : getEventsSameName
	 * Description : This method will look for an events from array having same name
	 * Accept : JSONArray (events), String (eventName) 
	 * Return : JSONArray
	 */
	public static JSONArray getEventsSameName(JSONArray events, String eventName) throws JSONException {
		JSONArray expectedEvents = new JSONArray();
		for (int i = 0; i < events.length(); i++)
			if (events.getJSONObject(i).getString("name").equalsIgnoreCase(eventName)) {
				expectedEvents.put(events.getJSONObject(i));
			}
		return expectedEvents;
	}
	
	/*
	 * Method name : getEventByProperty 
	 * Description : This method will look for an event by name and property
	 * Accept : JSONArray (events), String (name of event, name of property)
	 * Return : JSONObject (event)
	 */
	public static JSONObject getEventByProperty(JSONArray events, String eventName, String eventProperty) throws JSONException
	{
		JSONObject expectedEvent = null;
		boolean flag = false;
		for (int i = 0; i < events.length(); i++)
		{
			if (events.getJSONObject(i).getString("name").equalsIgnoreCase(eventName))
			{
				JSONObject proObj = events.getJSONObject(i).getJSONObject("properties");
				Iterator<String> proList = proObj.keys();
				while(proList.hasNext())
				{
					if (proList.next().contains(eventProperty))
					{
						expectedEvent = events.getJSONObject(i);
						flag = true;
						break;
					}
				}
				if(flag)
				{
					break;
				}
			}
		}
		return expectedEvent;
	}
	
	/*
	 * Method name : replaceEventPropertyValue 
	 * Description : This method will look for an event property and replace its value
	 * Accept : JSONObject (event), String (name of property, value of property)
	 * Return : boolean
	 */
	public static JSONObject replaceEventPropertyValue(JSONObject event, String propertyName, Object propertyValue) throws EventPropertyException
	{
		JSONObject properties;
		try {
			if (propertyName.contains("properties")) {
				event.getJSONObject("properties").put(propertyName.replace("properties.", ""), propertyValue);
				return event;
			}
			else
			{
				return event.put(propertyName, propertyValue);
			}
		} catch (JSONException e) {
			throw new EventPropertyException();
		}
	}

	/*
	 * Method name : verifyEventProperties Description : This method will look for
	 * an event property and check its value Accept : JSONObject (event), String
	 * (name of property, value of property) Return : boolean
	 */
	public static boolean verifyEventProperties(JSONObject event, String propertyName, String expectedPropertyValue) throws JSONException
	{
		Object actualValue = event.getJSONObject("properties").get(propertyName);
		if (actualValue.toString().equalsIgnoreCase(expectedPropertyValue)) {
			return true;
		} else {
			System.out.println(propertyName + " value is incorrect:");
			System.out.println("Actual value: " + actualValue);
			System.out.println("Expected value: " + expectedPropertyValue);
			return false;
		}
	}

	/*
	 * Method name : convertTextFileToJSONArray Description : This method will read
	 * data in text file and convert it to an json array Accept : String (text file
	 * name) Return : JSONArray
	 */
	public static JSONArray convertTextFileToJSONArray(String fileName) throws IOException, JSONException {
		String data = new String(
				Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/TestData/" + fileName)));
		return new JSONArray(data);
	}
	
	/*
	 * Method name : removeUnexpectedProperties 
	 * Description : This method will remove unexpected properties of events
	 * Accept : JSONArray (list of events), String[] (list of unexpected properties)
	 * Return : void
	 */
	public static void removeUnexpectedProperties(JSONArray events, String[] propertyNames) throws IOException, JSONException {
		for (int i=0; i<events.length(); i++)
		{
			for (int j=0; j<propertyNames.length; j++)
			{
				if(propertyNames[j].contains("properties."))
				{
					events.getJSONObject(i).getJSONObject("properties").remove(propertyNames[j].replace("properties.", ""));
				}
				else
				{
					events.getJSONObject(i).remove(propertyNames[j]);
				}
			}
		}
	}
	
	/*
	 * Method name : verifyEvent 
	 * Description : This method will compare 2 events
	 * Accept : JSONObject (actual & expected event)
	 * Return : boolean
	 */
	public static boolean verifyEvent(JSONObject actualEvent, JSONObject expectedEvent)
	{
		if(!actualEvent.toString().equalsIgnoreCase(expectedEvent.toString()))
		{
			System.out.println("Event is not correct!");
			System.out.println("Actual result:\n" + actualEvent);
			System.out.println("Expected result:\n" + expectedEvent);
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/*
	 * Method name : verifyGeneratedEventNames 
	 * Description : This method will compare 2 lists of event names
	 * Accept : String arrays (actual & expected event names)
	 * Return : boolean
	 */
	public static boolean verifyGeneratedEventNames(List<String> expected, List<String> actual)
	{
			Collections.sort(expected);
			Collections.sort(actual);
			if (expected.equals(actual))
			{
				return true;
			}
			else
			{
				System.out.println("expected: " + expected.size() + " events");
				System.out.println("actual: " + actual.size() + " events");
				System.out.println("events generated are:");
				for (int i = 0; i< actual.size(); i++)
				{
					System.out.println("event[" + i + "]: " + actual.get(i));
				}
				return false;
			}
	}
	
/**
 * This will verify event by property in case there are many duplicate events 
 * having the same name, properties but different property values
 * @param JSONArrays: List of events having same name
 * @param String: Name of property
 * @param String: Value of property
 * @return boolean: true if property value exists 
 * @throws JSONException 
 * */	
	public static boolean verifyEventByPropertyValue(JSONArray events, String propertyName, String propertyValue) throws JSONException
	{
		boolean result = false;
		for (int i = 0; i < events.length(); i++)
		{
			try
			{
				String value = events.getJSONObject(i)
						  .getJSONObject("properties")
						  .get(propertyName).toString();
				if(value.equalsIgnoreCase(propertyValue))
				{
					result = true;
					break;
				}
			}
			catch(JSONException e)
			{
				continue;
			}
		}
		if(!result)
		{
			System.out.println("Event with property value " + propertyValue + " does not exist!");
		}
		return result;
	}
	
}
