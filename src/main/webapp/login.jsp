<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.entity.*"%>
<%@page import="java.util.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	response.sendRedirect("index.jsp");
}
	
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session .getAttribute("cart-list");
	List<Cart> cartProducts = null;

	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp" %>
<title>Login Page</title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
	<div class="card w-50 mx-auto my-5">
		<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form method="post" action="user-login">
					<div class="form-group">
						<label>Email Address</label>
						<input type="email" class="form-control" name="login-email" placeholder="Enter Your Email ID" required>
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password" class="form-control" name="login-password" placeholder="************" required>
					</div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
	</div>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>