<%@page import="com.jspiders.springmvc3.dto.WebBlogDTO"%>
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
    WebBlogDTO blog = (WebBlogDTO) request.getAttribute("blog");
    %>

    <div align="center">

        <form action="./edit-blog" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <th><input type="text" readonly="readonly" 
                        name="id"  value="<%=blog.getId()%>"></th>
                </tr>

                <tr>
                    <th>Title</th>
                    <th><input type="text" required="required" name="title"
                        value="<%=blog.getTital()%>"></th> <!-- Fixed typo here -->
                </tr>

                <tr>
                    <th>Content</th>
                    <th><input type="text" required="required" name="content"
                         value="<%=blog.getContent()%>"></th>
                </tr>

                <tr>
                    <th>Author</th>
                    <th><input type="text" required="required" name="author"
                         value="<%=blog.getAuthor()%>"></th>
                </tr>
                
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="SUBMIT">
                    </td>
                </tr> <!-- Submit button with proper td tag -->
            </table>
        </form>
    </div>

</body>
</html>