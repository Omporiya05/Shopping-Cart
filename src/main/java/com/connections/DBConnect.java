package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect 
{
	private static Connection con = null;
	
	public static Connection getConnction()
	{
		if(con == null)
		{
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://localhost/ecommercedb","om","om123");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return con;
	}
}
