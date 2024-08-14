package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Order;
import com.entity.Product;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs ;
	
	public OrderDao(Connection con)
	{
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		
		try {
			query = "insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, model.getId());
			ps.setInt(2, model.getUid());
			ps.setInt(3, model.getQuantity());
			ps.setString(4,model.getDate());
			
			int executeUpdate = ps.executeUpdate();
			if(executeUpdate > 0) {
				result = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<>();
		
		try {
			
			query = "select * from orders where u_id = ? order by orders.o_id desc";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				Order order = new Order();
				ProductDao productdao = new ProductDao(this.con);
				
				int pid = rs.getInt("p_id");
				
				Product product = productdao.getSingleProduct(pid);
				order.setOrderid(rs.getInt("o_id"));
				order.setId(pid);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void cancelOrder(int id) {
		
		try {
			query = "delete from orders where o_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
