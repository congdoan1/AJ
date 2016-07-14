<%-- 
    Document   : login
    Created on : Apr 3, 2015, 1:50:26 AM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="CenterServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            <c:if test="${not empty requestScope.usernameErr}">
                <font color="red">${requestScope.usernameErr}</font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty requestScope.passwordErr}">
                <font color="red">${requestScope.passwordErr}</font><br/>
            </c:if>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
