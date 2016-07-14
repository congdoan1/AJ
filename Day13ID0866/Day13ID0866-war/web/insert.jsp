<%-- 
    Document   : insert
    Created on : Apr 1, 2015, 2:40:06 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h1>Insert</h1>
        <form action="CenterServlet">
            Username <input type="text" name="txtUsername" value="" /><br/>
            <c:if test="${not empty requestScope.usernameErr}">
                <font color="red">${requestScope.usernameErr}</font><br/>
            </c:if>
            Salary <input type="text" name="txtSalary" value="" /><br/>
            <c:if test="${not empty requestScope.salaryErr}">
                <font color="red">${requestScope.salaryErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.salaryFor}">
                <font color="red">${requestScope.salaryFor}</font><br/>
            </c:if>
            <input type="submit" value="Insert" name="btAction" />
        </form>
    </body>
</html>
