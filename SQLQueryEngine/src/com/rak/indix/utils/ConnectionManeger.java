package com.rak.indix.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManeger {

	
	private static String userId;
	private static String password;
	private static String url;
	
	static{
		Properties properties=new Properties();
		try {
			properties.load(new FileInputStream("resource\\dbdetails.properties"));
			userId=properties.getProperty("userid");
			password=properties.getProperty("password");
			url=properties.getProperty("url");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static Connection getConnection() throws SQLException {
		return getConnection(userId, password, url);
		
	}
	public static Connection getConnection(String user,String password,String dbUrl) throws SQLException{
		
		Connection connection=null;
		
		
		
			   Driver myDriver = new com.mysql.jdbc.Driver();
			   DriverManager.registerDriver( myDriver );
			
			   connection=DriverManager.getConnection(dbUrl, user, password);
			   
			   
			  
			   
		return connection;
		
	}
}
