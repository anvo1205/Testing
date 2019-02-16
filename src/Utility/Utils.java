package Utility;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Utils {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	/* Method name	: Initialize
	 * Description	: This method will instantiate a driver, set timeout, open based url and maximize the browser
	 * Accept		: void
	 * Return		: void	
	 */	
	public static void Initialize()
	{
		String browser = readXmlNodeByTagname(Constants.xml_File_Path, Constants.xml_Browser_Tagname);
		if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty(Constants.chromeDriver, Constants.chromeDriverPath);
			
			//Disable notification
			//Create prefs map to store all preferences 
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			//Create chrome options to set this prefs
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			Constants.driver = new ChromeDriver(options);
			
			//Headless Chrome settings
//			ChromeOptions options = new ChromeOptions();  
//			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors"); 
//			Constants.driver = new ChromeDriver(options);
			
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty(Constants.geckoDriver, Constants.geckoDriverPath);
			Constants.driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("safari"))
		{
			Constants.driver = new SafariDriver();
		}	
		Constants.wait = new WebDriverWait(Constants.driver, 10);
		Constants.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Constants.driver.get(Constants.Base_Url);
		Constants.driver.manage().window().maximize();
	}
	
	/* Method name	: captureWebElement
	 * Description	: This method will use driver to find an element 
	 * Accept		: By element
	 * Return		: WebElement	
	 */	
	public static WebElement captureWebElement(By element)
	{
		return Constants.driver.findElement(element);
	}
	
	/* Method name	: captureWebElements
	 * Description	: This method will use driver to find a list of elements
	 * Accept		: By element
	 * Return		: List<WebElement>	
	 */	
	public static List<WebElement> captureWebElements(By element)
	{
		return Constants.driver.findElements(element);
	}
	
	/* Method name	: moveMouseToElement
	 * Description	: This method will hover mouse to indicated element
	 * Accept		: By element
	 * Return		: void	
	 */	
	public static void moveMouseToElement(By element)
	{
		Actions action = new Actions(Constants.driver);
		action.moveToElement(captureWebElement(element)).build().perform();
	}
	
	/* Method name	: clickElement
	 * Description	: This method will wait until element is visible then click element
	 * Accept		: By element
	 * Return		: void	
	 */	
	public static void clickElement(By element)
	{
		waitUntilElementIsVisible(element);
		captureWebElement(element).click();
	}
	
	/* Method name	: inputValueIntoTextbox
	 * Description	: This method will clear existing value and input new value into text box
	 * Accept		: By element, String value
	 * Return		: void	
	 */	
	public static void inputValueIntoTextbox(By txt, String value)
	{
		WebElement textbox = Utils.captureWebElement(txt);
		waitUntilElementIsVisible(txt);
		if (!textbox.getAttribute("value").isEmpty() || !textbox.getText().isEmpty())
		{
			textbox.clear();
		}
		textbox.sendKeys(value);
	}
	
	/* Method name	: waitUntilElementIsVisible
	 * Description	: This method will wait until element is visible or timeout
	 * Accept		: By element
	 * Return		: void	
	 */	
	public static void waitUntilElementIsVisible(By element)
	{
		WebDriverWait wait = new WebDriverWait(Constants.driver, Constants.TimeOut);
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element is not visible");
		}	
	}
	
	/* Method name	: waitUntilElementInVisible
	 * Description	: This method will wait until element is invisible or timeout
	 * Accept		: By element
	 * Return		: void	
	 */	
	public static void waitElementInvisible(By element) {
		Constants.wait = new WebDriverWait(Constants.driver, Constants.TimeOut);
		try
		{
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element is visible");
		}	
		
	}
	
	/* Method name	: waitUntilElementInVisible
	 * Description	: This method will wait until element is invisible or timeout defined by test
	 * Accept		: By element, int (seconds)
	 * Return		: void	
	 */	
	public static void waitElementInvisible(By element, int timeoutInSeconds) {
		Constants.wait = new WebDriverWait(Constants.driver, timeoutInSeconds);
		try
		{
			Constants.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Element is visible");
		}	
		
	}
	
	/* Method name	: generateRandomEmail
	 * Description	: This method will generate a random email using current date time
	 * Accept		: void
	 * Return		: String (email)	
	 */	
	public static String generateRandomEmail(String prefix)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = simpleDateFormat.format(new Date());
		if (prefix.isEmpty())
		{
			return "test" + dateAsString + "@vengage.com";
		}
		else
		{
			return prefix + dateAsString + "@venngage.com";
		}
	}

	/* Method name	: generateRandomInteger
	 * Description	: This method will return an integer value within a range
	 * Accept		: int (min and max)
	 * Return		: int (random value)
	 */	
	public static int generateRandomInteger(int min, int max)
	{
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

	/* Method name	: selectDropDownListValue
	 * Description	: This method will select an option from dropdown list by text displays
	 * Accept		: By (element), String (tagName of options), String (text value)
	 * Return		: void
	 */	
	public static void selectDropDownListValue(By ddl, String tagName, String value) {
		List<WebElement> listItems = captureWebElement(ddl).findElements(By.tagName(tagName));
		for (WebElement item: listItems)
		{
			if (item.getText().equalsIgnoreCase(value))
			{
				item.click();
			}
		}	
	}
	
	/* Method name	: getDropDownListItems
	 * Description	: This method will get all list options from a dropdown
	 * Accept		: By (element), String (tagName of options)
	 * Return		: void
	 */	
	public static List<String> getDropDownListItems(By ddl, String tagName) {
		List<String> list = new ArrayList<String>();
		List<WebElement> listItems = captureWebElement(ddl).findElements(By.tagName(tagName));
		for (WebElement item: listItems)
		{
			list.add(item.getText());
		}	
		return list;
	}
	
	/* Method name	: readXmlNodeById
	 * Description	: This method will read the content of an xml node by id
	 * Accept		: String (file path including file name, id of xml node)
	 * Return		: String (xml node content)
	 */	
	public static String readXmlNodeByTagname(String filePath, String tagName)
	{
		String result = "";
		try
		{
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			Node node = doc.getElementsByTagName(tagName).item(0);
			result = node.getTextContent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/* Method name	: scrollToElement
	 * Description	: This method will scroll the view to expected element
	 * Accept		: By (element)
	 * Return		: void
	 */	
	public static void scrollToElement(By element) throws InterruptedException
	{
		((JavascriptExecutor) Constants.driver).executeScript("arguments[0].scrollIntoView(true);", captureWebElement(element));
		Thread.sleep(500); 
	}
	
	/* Method name	: dragAndDrop
	 * Description	: This method will drag and drop an element
	 * Accept		: By (element), int (x-axis), int (y-axis)
	 * Return		: void
	 */	
	public static void dragAndDropElement(By element, int x, int y)
	{
		Actions action = new Actions(Constants.driver);
		action.dragAndDropBy(captureWebElement(element), x, y);
	}

	/* Method name	: byPassGoogleCapcha
	 * Description	: This method will execute Javascript to bypass google capcha
	 * Accept		: void
	 * Return		: void
	 */	
	public static void byPassGoogleCapcha()
	{
		((JavascriptExecutor) Constants.driver).executeScript("$('#g-recaptcha-response').val('test');");
		((JavascriptExecutor) Constants.driver).executeScript("$('#sign_in_form').submit();");
	}
	
	/* Method name	: deleteFile
	 * Description	: This method will delete a file specified by filePath
	 * Accept		: String filePath
	 * Return		: void
	 */	
	public static boolean deleteFile(String filePath)
	{
		File f = new File(filePath);
		if (f.exists()){
			return f.delete();
		 }
		else
		{
			return false;
		}
	}
	
	/**
	 * This method will take the unixDate as long value, then convert it to calendar date
	 * @param long: unixDate
	 * @return date: calendar date
	 * */
	public static Date convertUnixTimeToCalendarDate(long unixDate)
	{
		return new Date(unixDate * 1000);
	}
	
	/**
	 * This method will add a number of days to calendar date
	 * @param Date: current date
	 * @param int: number of days
	 * @return date: calendar date
	 * */
	public static Date addDays(Date date, int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, days);
		return gc.getTime();
	}
	
	/**
	 * This method converts a date to a string in provided format
	 * @param Date
	 * @param String
	 * @return String
	 * */
	public static String formatDateString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * This method converts unix time to calendar date and get date only
	 * @param Long
	 * @return String
	 * */
	public static String getDateFromDateTime(Date date)
	{
		String[] dateTime = date.toString().split(" ");
		return dateTime[0] + dateTime[1] + dateTime[2] + dateTime[5];
	}
	
	/* Method name	: unzipFile
	 * Description	: This method will delete a file specified by filePath
	 * Accept		: String (zipFilePath, destination Directory)
	 * Return		: void
	 */		
	public static void unzipFile(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void unZipIt(String zipFile, String outputFolder){

	     byte[] buffer = new byte[1024];
	    	
	     try{
	    		
	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	    		
	    	//get the zip file content
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(zipFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	    		
	    	while(ze!=null){
	    			
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	                
	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
	                
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	              
	            FileOutputStream fos = new FileOutputStream(newFile);             

	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	        		
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	    	
	        zis.closeEntry();
	    	zis.close();
	    		
	    	System.out.println("Done");
	    		
	    }catch(IOException ex){
	       ex.printStackTrace(); 
	    }
	   } 

//	public static void unzip(File source, String out) throws IOException {
//	    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {
//
//	        ZipEntry entry = zis.getNextEntry();
//
//	        while (entry != null) {
//
//	            File file = new File(out, entry.getName());
//
//	            if (entry.isDirectory()) {
//	                file.mkdirs();
//	            } else {
//	                File parent = file.getParentFile();
//
//	                if (!parent.exists()) {
//	                    parent.mkdirs();
//	                }
//
//	                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
//
//	                    byte[] buffer = new byte[Math.toIntExact(entry.getSize())];
//
//	                    int location;
//
//	                    while ((location = zis.read(buffer)) != -1) {
//	                        bos.write(buffer, 0, location);
//	                    }
//	                }
//	            }
//	            entry = zis.getNextEntry();
//	        }
//	    }
//	}
	
	/**Method name	: compareImages
	 * Description	: This method will compare 2 images pixel by pixel
	 * Accept		: String (source and destination file path)
	 * Return		: boolean
	 * @throws IOException 
	 */	
	public static boolean compareImages(String srcFilePath, String desFilePath) throws IOException
	{
		boolean result = true;
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		try {
			File src = new File(srcFilePath);
			File des = new File(desFilePath);
			
			//Read and store images in RAM
			img1 = ImageIO.read(src);
			img2 = ImageIO.read(des);
		}
		catch(Exception e)
		{
			System.out.println("File not found");;
		}
		
		int widthImg1 = img1.getWidth();
		int heightImg1 = img1.getHeight();
		
		int widthImg2 = img2.getWidth();
		int heightImg2 = img2.getHeight();
		
		if(widthImg1 != widthImg2 || heightImg1 != heightImg2)
		{
			System.out.println("Dimention is different");
			result = false;
		}
		else
		{
		    //Create output Buffered image of type RGB
		    BufferedImage outImg = new BufferedImage(widthImg1, heightImg1, BufferedImage.TYPE_INT_RGB);
			int dif = 0;
			int result1 = 0;
			for (int h = 0; h < heightImg1; h++)
			{
				for (int w = 0; w < widthImg1; w++)
				{
					//The structure of ARGB is A(bit 31-24),
					//R(bit 23-16), G(bit 15-8), B(7-0)
					int pixelImg1 = img1.getRGB(h, w);
					int r1 = (pixelImg1 >> 16) & 0xff;
					int g1 = (pixelImg1 >> 8) & 0xff;
					int b1 = pixelImg1 & 0xff;
					
					int pixelImg2 = img2.getRGB(h, w);
					int r2 = (pixelImg2 >> 16) & 0xff;
					int g2 = (pixelImg2 >> 8) & 0xff;
					int b2 = pixelImg2 & 0xff;
					
					dif += Math.abs(r1 - r2);
					dif += Math.abs(g1 - g2);
					dif += Math.abs(b1 - b2);
					
					dif /= 3;
					result1 = (dif << 16) | (dif << 8) | dif;
		            outImg.setRGB(w, h, result1);
					}
				}
			if(dif != 0)
			{
	            ImageIO.write(outImg, "png", new File(Constants.Download_Destination +  "/dif.png"));	
				System.out.println("Images are different");
				result = false;
			}
		}
		return result;
	}
	
	/**Method name	: getDifferenceImage
	 * Description	: This method will compare 2 images pixel by pixel and produce the difference
	 * Accept		: BufferedImage (source and destination images)
	 * Return		: boolean (true if there is no difference)
	 * @throws IOException 
	 */		
	public static boolean compareTwoImages(String srcFilePath, String desFilePath) throws IOException {
	    boolean result = true;
	    
	    //Read file to buffer image
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		try {
			File src = new File(srcFilePath);
			File des = new File(desFilePath);
			
			//Read and store images in RAM
			img1 = ImageIO.read(src);
			img2 = ImageIO.read(des);
		}
		catch(Exception e)
		{
			System.out.println("File not found");;
		}
	    
		//Convert images to pixel arrays...
	    int w = img1.getWidth();
	    int h = img1.getHeight(); 
	    int highlight = Color.MAGENTA.getRGB();
	    int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    
	    // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	            System.out.println("different at pixel " + p1[i]);
	            result = false;
	        }
	    }
	    //Save img1's pixels to a new BufferedImage
	    //(May require TYPE_INT_ARGB)
	    if (!result)
	    {
	    	BufferedImage difImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		    difImg.setRGB(0, 0, w, h, p1, 0, w);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
			String dateAsString = simpleDateFormat.format(new Date());
		    ImageIO.write(difImg, "png", new File(Constants.Download_Destination +  "/dif " + dateAsString + ".png"));
	    }
	    return result;
	}
	
	
	
}
