<%@page import="com.jspiders.springmvc3.dto.WebBlogDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jspiders.springmvc3.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<body>

	<a href="back-home">HOME</a>


	<%
	List<WebBlogDTO> blogs = (List<WebBlogDTO>) request.getAttribute("blogs");
	%>

	<div align="center">

		<form action="">

			<%
			if (blogs.size() > 0 && (blogs!=null)) {
				for (WebBlogDTO blog : blogs) {
			%>
			<br>
			<table border="1px solid" style="text-align: center;">
				<tr>
					<th>TITAL</th>
					<th>CONTENT</th>
					<th>DATE</th>
					<th>AUTHOR</th>
					<th colspan="2">ACTION</th>
				</tr>
				<tr>
					<td><%=blog.getTital()%></td>
					<td><%=blog.getContent()%></td>
					<td><%=blog.getDate()%></td>
					<td><%=blog.getAuthor()%></td>
					<td><a href="update-blog?id=<%=blog.getId()%>">Update</a></td>
					<td><a href="delete-blog?id=<%=blog.getId()%>">Delete</a></td>
				</tr>
			</table>
			<br>
			<hr>
			<hr>
			<%
			}
			}else{
			%>

			<%
			String message = (String) request.getAttribute("message");
			%>
			<h2><%=message %></h2>
			<%} %>
		</form>
	</div>


</body>
</html>