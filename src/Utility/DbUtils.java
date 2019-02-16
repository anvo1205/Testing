package Utility;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.Statement;
	import com.jcraft.jsch.JSch;
	import com.jcraft.jsch.Session;
	import java.sql.Connection;
	import java.util.ArrayList;
import java.util.Properties;

public class DbUtils {
		
		// Define variables for the connection
	    static int LocalPort=7777;								// local port number used to bind the SSH tunnel
	    
	    
	    static String RemoteHost="yolo-db.c5xh6y571zd7.ca-central-1.rds.amazonaws.com";
	    static int Remoteport=3306;
	    static String SSHHost="ec2-35-182-237-17.ca-central-1.compute.amazonaws.com";
	    static int SSHPort=22; // remote SSH host port number
	    static String SSHUser="ubuntu"; // SSH login username
	    static String SSHPassword=""; //SSH login password
	    static String DBUserName="awsmaster"; //DB database login username
	    static String DBPassword="venngage!!123"; // DB database login password
	    static String DataBase="vizmvp"; // DB database target
	    static String url="jdbc:mysql://localhost:" + LocalPort + "/" + DataBase;
	    static String driverName="com.mysql.cj.jdbc.Driver";
	    static Connection conn=null;
	    static Session session=null;
	    static Statement stmt;
	    static ResultSet rs;

		public static void DBSetup() throws Exception{
			
			try 
	        {
//			Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
	        Properties config = new Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        JSch jsch = new JSch();
	        jsch.addIdentity("C:/Users/John/.ssh/id_rsa","");
	      
	       session = jsch.getSession(SSHUser, SSHHost, 22);
	       session.setConfig("StrictHostKeyChecking", "no");
	        session.setPassword(SSHPassword);
	        session.setConfig(config);
	        session.connect();
	        System.out.println("Connected to the Database Server");
	        int assigned_port=session.setPortForwardingL(LocalPort, RemoteHost, Remoteport);
	        System.out.println("localhost:"+assigned_port+" -> " + RemoteHost + ":" + Remoteport);
	        System.out.println("Port Forwarded");
	         
	        //mysql database connectivity     
	        Class.forName(driverName);
	        conn = DriverManager.getConnection (url, DBUserName, DBPassword);
	        System.out.println ("Database connection established" + "\n");
	        
	        }
			catch (Exception e) 
	        {	System.out.println("catch");
	        	session.disconnect();
				e.printStackTrace();
			}
		}

		public static ArrayList<String> DBReturnArrayResult(String Query) throws Exception{
		
			stmt = conn.createStatement(); 
			
	        ArrayList<String> ArrayData = new ArrayList<String>();
	        
			try{     
	        ResultSet rs = stmt.executeQuery(Query);
	        ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();
	                    
	        //Display of Result
	        while(rs.next()){
				
				for (int col=1; col <= colCount; col++) 
			    {
			        Object value = rs.getObject(col);
			        
			        if (value != null) 
			        {
			        	ArrayData.add(String.valueOf(value));
			        }
			    }
			}

	        return ArrayData;
		}
		
			catch(Exception e){
	            e.printStackTrace();
			}
			return null;	
		}

		public static void DBExecQuery(String Query) throws Exception{
					
			stmt = conn.createStatement();
			
			try{	
				@SuppressWarnings("unused")
				int res = stmt.executeUpdate(Query);
			}
			catch(Exception e){
	            e.printStackTrace();
			}
			
		}
		
		public static String DBReturnResult(String Query) throws Exception{
			
			stmt = conn.createStatement(); 
			
			try{ 
			
	        ResultSet rs = stmt.executeQuery(Query);
	        
	        //Display of Result
	        while(rs.next()){
	        	String result =rs.getString(1);
	        	System.out.println(result);
	        	return result;
			}
		}
		
			catch(Exception e){
				
	            e.printStackTrace();
				System.out.println("este es el error: " + e.getMessage());	
	        
			}
			return "SSHDBReturnResult failed";	
		}
		
		public static void DBCloseConnection() throws Exception{
		
	        	//Closing connections
	        	if(conn != null || !conn.isClosed()){
	                System.out.println("\n" + "Closing Database Connection");
	                conn.close();
	            }
	            if(session !=null || session.isConnected()){
	                System.out.println("Closing SSH Connection");
	                session.disconnect();
			}
		}
		
		@SuppressWarnings("static-access")
		public static String[][] DBReturnMatrixResult(String Query) throws Exception{
			
			stmt = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY); 
			
			String[][] QueryResult = null;
			
			stmt.setFetchSize(400);
			
			try
			{ 
				//System.out.println("este es el query: " + Query + "\n");
				ResultSet rs = stmt.executeQuery(Query);
				ResultSetMetaData meta = rs.getMetaData();
				//to get the quantity of columns for the matrix
				int colCount = meta.getColumnCount();
				//go to the last row of the results
				rs.last();
				//count the last item to get quantity of rows of the matrix
				int rowCount = rs.getRow();
				QueryResult = new String[rowCount][colCount];
				rs.beforeFirst();;
			
				while (rs.next())
				{
					for (int RowIndex=1; RowIndex <= rowCount; RowIndex++)
					{  
						for (int ColumnIndex=1; ColumnIndex <= colCount; ColumnIndex++) 
						{
							System.out.println("COLUMNA Nº: " + ColumnIndex);
							System.out.println("este es el valor del rs: " + rs.getObject(ColumnIndex));
							QueryResult[RowIndex - 1][ColumnIndex - 1] = String.valueOf(rs.getObject(ColumnIndex));
						}
						//to move to the next row in the resulset
						rs.next();
					}
				}
			}
		
			catch(Exception e)
			{
	            e.printStackTrace();
	        
			}
			return QueryResult;
		}
	}
