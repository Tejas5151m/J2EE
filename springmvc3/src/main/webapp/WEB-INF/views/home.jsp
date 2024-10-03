<%@page import="com.jspiders.springmvc3.dto.Role"%>
<%@page import="com.jspiders.springmvc3.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Home Page</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif; /* Use a modern font */
	margin: 0;
	padding: 0;
	background-color: #f4f4f4; /* Light background color */
	color: #333; /* Dark text color for better readability */
}

nav {
	display: flex;
	justify-content: space-around;
	align-items: center; /* Center items vertically */
	background-color: #007bff; /* Primary color */
	padding: 15px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
}

nav h2 {
	color: white; /* White text for the title */
	margin: 0; /* Remove default margin */
	color: yellow;
}

nav a {
	text-decoration: none;
	color: white; /* White link color */
	padding: 10px 15px;
	border-radius: 5px; /* Rounded corners */
	transition: background-color 0.3s; /* Smooth background change */
}

nav a:hover {
	background-color: #0056b3; /* Darker blue on hover */
}

h3 {
	text-align: center; /* Center align the message */
	color: #d9534f; /* Alert color for messages */
}

footer {
	text-align: center;
	padding: 20px;
	background-color: #007bff;
	color: white; /* White text for footer */
	position: fixed;
	width: 100%;
	bottom: 0; /* Fixed to the bottom of the page */
}
</style>
</head>
<body>
	<%
	UserDTO user = (UserDTO) request.getAttribute("user");
	%>

	<nav>
		<h2>Keep Blogging..!!</h2>
		<a href="logout">LOG OUT</a> 
		<a href="update">UPDATE</a>
		<a href="delete-user">DELETE ACCOUNT</a>
		<%
		if (user.getRole().equals(Role.USER)) {
		%>
		<a href="view-blog">MY BLOG</a> 
		<a href="add-blog">ADD BLOG</a>
		<%
		}
		%>

		<a href="all-blog">ALL BLOG</a>
		
		<%
		if (!user.getRole().equals(Role.USER)) {
		%>
		<a href="all-users">ALL USERS</a>
		<%
		}
		%>
	</nav>


	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<h3><%=message%></h3>
	<%
	}
	%>

</body>
</html>
