package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Cart;
import com.entity.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs ;
	
	public ProductDao(Connection con)
	{
		this.con = con;
	}
	
	public List<Product> getAllProduct()
	{
		List<Product> list = new ArrayList<>();
		Product product = null;
		
		try {
			query = "select * from products";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setImage(rs.getString("image"));
				product.setPrice(rs.getDouble("price"));
				
				list.add(product);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList)
	{
		List<Cart> products = new ArrayList<Cart>();
		
		try{
			if(cartList.size() > 0){
				for(Cart item : cartList) {
					query = "select * from products where id = ?";
					ps = con.prepareStatement(query);
					ps.setInt(1, item.getId());
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			//e.printStackTrace();		
		}
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList)
	{
		double sum = 0;
		
		try {
			if(cartList.size() > 0)
			{
				for(Cart item : cartList) {
					query = "select * from products where id = ?";
					ps = con.prepareStatement(query);
					ps.setInt(1, item.getId());
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						sum = sum +  (rs.getDouble("price") * item.getQuantity());
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Product getSingleProduct(int id) {
		
		Product row = null;
		
		try {
			query = "select * from products where id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
