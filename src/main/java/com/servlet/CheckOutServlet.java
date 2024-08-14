package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.connections.DBConnect;
import com.dao.OrderDao;
import com.entity.Cart;
import com.entity.Order;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date  date = new Date();
		
		try(PrintWriter out = response.getWriter())
		{
			//retrieve all cart Products
			ArrayList<Cart> cart_list = (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			User auth = (User)request.getSession().getAttribute("auth");
			
			if(cart_list!=null && auth!=null) 
			{
				for(Cart c: cart_list) {
					//prepare the order object
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate the dao class
					OrderDao odao = new OrderDao(DBConnect.getConnction());
					
					//calling the dao class
					boolean result = odao.insertOrder(order);
					if(!result) {
						break;
					}
					
				}
				
				cart_list.clear();
				response.sendRedirect("order.jsp");
			}
			else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("Cart.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
