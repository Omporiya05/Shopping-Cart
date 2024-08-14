package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao 
{
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs ;
	
	public UserDao(Connection con)
	{
		this.con = con;
	}
	
	public User userLogin(String email,String password)
	{
		User user = null;
		
		try {
			query = "select * from user1 where email = ? and password = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
}
