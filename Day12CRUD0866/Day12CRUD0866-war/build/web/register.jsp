<%-- 
    Document   : register
    Created on : Mar 31, 2015, 4:38:08 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="CenterServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6-20 char)<br/>
            <c:if test="${not empty requestScope.usernameErr}">
                <font color="red">${requestScope.usernameErr}</font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6-20 char)<br/>
            <c:if test="${not empty requestScope.passwordErr}">
                <font color="red">${requestScope.passwordErr}</font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty requestScope.confirmErr}">
                <font color="red">${requestScope.confirmErr}</font><br/>
            </c:if>
            Lastname* <input type="text" name="txtLastname" value="${param.txtLastname}" />(5-30 char)<br/>
            <c:if test="${not empty requestScope.lastnameErr}">
                <font color="red">${requestScope.lastnameErr}</font><br/>
            </c:if>
            <input type="submit" value="Create" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${not empty requestScope.usernameDup}">
            <font color="red">${requestScope.usernameDup}</font>
        </c:if>
    </body>
</html>
