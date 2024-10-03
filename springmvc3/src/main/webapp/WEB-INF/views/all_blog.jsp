<%@page import="com.jspiders.springmvc3.dto.Role"%>
<%@page import="com.jspiders.springmvc3.dto.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jspiders.springmvc3.dto.WebBlogDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blog List</title>
</head>
<body>

	<a href="back-home">HOME</a>

	<%
	List<WebBlogDTO> blogs = (List<WebBlogDTO>) request.getAttribute("blogs");
	UserDTO user = (UserDTO) request.getAttribute("user");
	Role role = user.getRole();
	%>

	<%
	if ((blogs != null && blogs.size() > 0) && role.equals(Role.USER)) {
		for (WebBlogDTO blog : blogs) {
	%>
	<hr>
	<br>
	<div align="center">
		<form action="">
			<table border="1"
				style="text-align: center; border: 1px solid black;">
				<tr>
					<th>TITLE</th>
					<th>CONTENT</th>
					<th>DATE</th>
					<th>AUTHOR</th>
					<%
					if (!user.getRole().equals(Role.USER)) {
					%>
					<th colspan="2">ACTION</th>
					<%
					}
					%>

				</tr>
				<tr>
					<td><%=blog.getTital()%></td>
					<td><%=blog.getContent()%></td>
					<td><%=blog.getDate()%></td>
					<td><%=blog.getAuthor()%></td>
					<%
					if (!user.getRole().equals(Role.USER)) {
					%>
					<td><a href="delete-blog?id=<%=blog.getId()%>">Delete</a></td>
					<%
					}
					%>

				</tr>
			</table>
		</form>
	</div>
	<br>
	<%
	}
	} else {
	for (WebBlogDTO blog : blogs) {
	%>
	<hr>
	<br>
	<div align="center">
		<form action="">
			<table border="1"
				style="text-align: center; border: 1px solid black;">
				<tr>
					<th>TITLE</th>
					<th>CONTENT</th>
					<th>DATE</th>
					<th>AUTHOR</th>
					<th>ACTION</th>
				</tr>
				<tr>
					<td><%=blog.getTital()%></td>
					<td><%=blog.getContent()%></td>
					<td><%=blog.getDate()%></td>
					<td><%=blog.getAuthor()%></td>
					<td><a
						href="delete-userBlog?id=<%=blog.getId()%>&user-id=<%=user.getId()%>">Delete</a></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<%
	}
	}
	%>

	<%
	if (blogs == null || blogs.size() == 0) {
	%>
	<div align="center">
		<h1>NO BLOG PRESENT</h1>
	</div>
	<%
	}
	%>
</body>
</html>
