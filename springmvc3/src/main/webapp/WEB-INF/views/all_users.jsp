<%@page import="com.jspiders.springmvc3.dto.Role"%>
<%@page import="com.jspiders.springmvc3.dto.UserDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
body {
	font-family: Arial, sans-serif;
}

form {
	width: 100%;
	max-width: 1000px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	border: 1px solid;
}

td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
	border: 1px solid;
}

th {
	border: 1px solid;
}

input[type="text"] {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
}

tr:first-child td {
	font-weight: bold;
	border: 1px solid;
}

a {
	text-decoration: none;
}

.adminStyle {
	background-color: #f8ee90
}

.admin-bg {
	color: red;
	font-weight: bold;
}

.bloked {
	background-color: #fd5b5b;

}

.unbloked {
	background-color: rgb(135, 253, 135);
	
}

.headings {
	background-color: skyblue;
	height: 50px;
}
</style>


</head>
<body>

	<a href="back-home">HOME</a>
		<!-- WELCOMING THE USER -->
	 <% UserDTO admin = (UserDTO) request.getAttribute("user");
	 String name = admin.getUserName();
	 name = name.toUpperCase();
	 %>
	

	<%
	List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
	if (users.size() > 0) {
	%>

	
	  <h1> <div align="center"> WELCOME <%=name %>.</div> </h1>
	<!-- WELCOMING THE USER -->


	<div align="center">
		<form action="">
			<table>
				<tr class="headings">
					<th>USERNAME</th>
					<th>PASSWORD</th>
					<th>EMAIL</th>
					<th>MOBILE</th>
					<th>PASSWORD</th>
					<th>BLOCK</th>
				</tr>
				<%
				for (UserDTO user : users) {
					Role role = user.getRole();
				%>
				<tr>

					<%
					if (role.equals(Role.ADMIN)) {
					%>

					<td class="adminStyle"><%=user.getUserName()%></td>
					<td class="adminStyle"><%=user.getPassword()%></td>
					<td class="adminStyle"><%=user.getEmail()%></td>
					<td class="adminStyle"><%=user.getMobile()%></td>
					<td class="adminStyle"><%=user.getPassword()%></td>

					<%
					} else {
					%>

					<td><%=user.getUserName()%></td>
					<td><%=user.getPassword()%></td>
					<td><%=user.getEmail()%></td>
					<td><%=user.getMobile()%></td>
					<td><%=user.getPassword()%></td>

					<%
					}
					%>

					<%
					if (user.isBlock() && (role.equals(Role.USER))) {
					%>
					<td class="bloked"><a
						href="unblockUser?userId=<%=user.getId()%>">UNBLOCK</a></td>

					<%
					} else if (!user.isBlock() && (role.equals(Role.USER))) {
					%>
					<td class="unbloked" style="color: blue"><a
						href="blockUser?userId=<%=user.getId()%>">BLOCK</a></td>
					<%
					} else {
					%>
					<td class="admin-bg" style="background-color: #f8ee90">YOU</td>

					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</table>


		</form>
	</div>

	<%
	}
	%>

</body>
</html>