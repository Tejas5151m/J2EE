<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
/* General page styling */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f9;
    margin: 0;
    padding: 0;
}

a {
    display: inline-block;
    margin: 20px;
    text-decoration: none;
    color: #0073e6;
    font-size: 16px;
}

a:hover {
    text-decoration: underline;
}

/* Center the form on the page */
div[align="center"] {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80vh;
}

/* Table styling */
table {
    border-collapse: collapse;
    width: 400px;
    background-color: #fff;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

td {
    padding: 10px;
    font-size: 14px;
}

input[type="text"],
textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
}

textarea {
    resize: none;
}

input[type="submit"] {
    background-color: #0073e6;
    color: white;
    border: none;
    padding: 10px 20px;
    text-transform: uppercase;
    cursor: pointer;
    font-size: 14px;
}

input[type="submit"]:hover {
    background-color: #005bb5;
}

table tr:nth-child(odd) {
    background-color: #f9f9f9;
}


</style>

</head>
<body>

	<a href="back-home">HOME</a>


	<div align="center">

		<form action="./insert-blog" method="post">
			<table style="border: 1px solid;">
				<tr>
					<td>tital</td>
					<td><input type="text" required="required" name="title"></td>
				</tr>

				<tr>
					<td>content</td>
					<td><textarea name="content" required="required" id="" cols="30" rows="15"></textarea></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><input type="text" name="author"></td>
				</tr>
				<tr>
					<td><input type="submit" name="SUBMIT"></td>
				</tr>

			</table>
		</form>

	</div>

</body>
</html>