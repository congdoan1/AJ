<%-- 
    Document   : show
    Created on : Mar 22, 2015, 6:10:54 PM
    Author     : Suzy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show</title>
    </head>
    <body>
        <h1>Your Shopping Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="CenterServlet">
                    <c:set var="count" value="0"/>
                    <c:forEach var="rows" items="${cart}">
                        <c:set var="count" value="${count + 1}"/>
                        <tr>
                            <td>${count}</td>
                            <td>${rows.value.title}</td>
                            <td>${rows.value.quantity}</td>
                            <td><input type="checkbox" name="rmv" value="${rows.value.title}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <c:url var="add" value="CenterServlet">
                            <c:param name="btAction" value="Add More"/>
                        </c:url>
                        <td colspan="3"><a href="${add}">Add More</a></td>
                        <td><input type="submit" value="Remove" name="btAction"/></td>
                    </tr>
                    </tbody>
                </form>
            </table>

        </c:if>
    </body>
</html>
