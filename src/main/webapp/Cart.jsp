<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.entity.*"%>
<%@page import="java.util.*" %>
<%@page import="com.connections.*" %>
<%@page import="com.dao.*" %>
<%@page import="java.text.*" %>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

	User auth = (User) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session .getAttribute("cart-list");
List<Cart> cartProducts = null;

if(cart_list != null){
	ProductDao pdao = new ProductDao(DBConnect.getConnction());
	cartProducts = pdao.getCartProducts(cart_list);
	double total = pdao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total",total);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Page</title>
<%@include file="includes/header.jsp"%>
<style>
.table tbody td{
	vartical-align : middle;
}
.btn-incre, .btn-decre{
	box-shadow:none;
	font-size:25px;
}
</style>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price : $ ${(total>0)?dcf.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="cart-check-out"> Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(cart_list!=null){
						for(Cart c: cartProducts)
						{
				%>
							<tr>
							<td><%= c.getName() %></td>
							<td><%= c.getCategory() %></td>
							<td><%= dcf.format(c.getPrice()) %></td>
							<td>
								<form method="post" action="order-now" class="form-inline">
									<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
									<div class="form-group d-flex justify-content-between">
										<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%= c.getId() %>"><i
											class="fas fa-minus-square"></i></a> <input type="text"
											name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly>
										<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId() %>"><i
											class="fas fa-plus-square"></i></a>
									</div>
									
									<button type="submit" class="btn btn-primary nbtn-sm" >Buy</button>
								</form>
							</td>
							<td> <a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId() %>"> Remove </a> </td>
						</tr>
						<%}
					}
				%>
				
			</tbody>
		</table>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>