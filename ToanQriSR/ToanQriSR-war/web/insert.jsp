<%-- 
    Document   : insert
    Created on : Apr 1, 2015, 10:27:04 AM
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
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <h1>Insert Book</h1>
        <form action="CenterServlet">
            ISBN <input type="text" name="txtISBN" value="" /><br/>
            <c:if test="${not empty requestScope.isbnErr}">
                <font color="red">${requestScope.isbnErr}</font><br/>
            </c:if>
            Title <input type="text" name="txtTitle" value="" /><br/>
            <c:if test="${not empty requestScope.titleErr}">
                <font color="red">${requestScope.titleErr}</font><br/>
            </c:if>
            Price <input type="text" name="txtPrice" value="" /><br/>
            <c:if test="${not empty requestScope.priceErr}">
                <font color="red">${requestScope.priceErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.priceFor}">
                <font color="red">${requestScope.priceFor}</font><br/>
            </c:if>
            <input type="submit" value="Insert" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${not empty requestScope.isbnDup}">
            <font color="red">${requestScope.isbnDup}</font>
        </c:if>
    </body>
</html>
