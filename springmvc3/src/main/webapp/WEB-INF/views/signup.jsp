<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    
    <style type="text/css">
    
    /* Body Styling */
body {
    font-family: Arial, sans-serif;
    background-color: #e0f7fa; /* Light teal background */
}

/* Heading (h1) Styling */
h1 {
	margin-top:-180px;
    font-family: 'Georgia', serif;
    font-weight: bold;
    color: #00796b; /* Teal color for the heading */
    border-radius: 10px;
}

/* Form Styling */
form {
    width: 400px;
    height: auto;
    padding: 20px;
    border: 1px solid royalblue;
    border-radius: 10px;
    background-color: #ffffff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease, transform 0.3s ease; /* Smooth transition */
}

/* Form hover effect */
form:hover {
    box-shadow: 1px 1px 5px red;
    transform: scale(1.02); /* Slight scaling effect on hover */
}

/* Table Styling */
table {
    width: 100%;
}

/* Table cells (td) Styling */
td {
    padding: 10px;
    font-size: 14px;
    color: #333; /* Darker text color */
}

/* Input fields and select box styling */
input[type="text"], select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    background-color: #f9f9f9; /* Light gray background for input fields */
}

/* Focus effect on input fields and select box */
input[type="text"]:focus, select:focus {
    border-color: #66afe9; /* Light blue border on focus */
    outline: none;
    box-shadow: 0 0 5px rgba(102, 175, 233, 0.5); /* Blue shadow effect */
}

/* Submit button styling */
input[type="submit"] {
    color: blue;
    background-color: #f0f0f0; /* Light background for button */
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    width: 100%;
}

/* Submit button hover effect */
input[type="submit"]:hover {
    background-color: #dcdcdc;
}

/* Link styling */
a {
    color: #00796b; /* Teal color for link */
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
    
    
    </style>
    
</head>
<body>

    <div align="center" style="margin-top: 200px">

        <h1>WELCOME TO APP</h1>

        <h5 style="margin-top: -25px">Please fill in the following details...!!</h5>

        <form action="./user" method="post">
            <table>
                <tr>
                    <td>USERNAME</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>EMAIL</td>
                    <td><input type="text" name="email" required></td>
                </tr>
                <tr>
                    <td>PASSWORD</td>
                    <td><input type="text" name="password" required></td>
                </tr>
                <tr>
                    <td>CONTACT</td>
                    <td><input type="text" name="contact" required></td>
                </tr>
                <tr>
                    <td>ROLE</td>
                    <td>
                        <select name="role" required style= " width: 175px"  >
                            <option disabled selected value="">SELECT</option>
                            <option value="USER">USER</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="SUBMIT"></td>
                </tr>
            </table>
            <h5>
                Already a user? Click here to <a href="login">Login..!</a>
            </h5>
        </form>

        <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
        %>
        <h3><%=message%></h3>
        <%
        }
        %>
    </div>

</body>
</html>
