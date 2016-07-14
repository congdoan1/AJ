<%-- 
    Document   : cart
    Created on : Mar 29, 2015, 7:00:39 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Shopping Cart</title>
    </head>
    <body>
        <h1>View Cart Demo</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.items}"/>
        <c:if test="${not empty cart and not empty items}">
            <form action="CenterServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}" varStatus="counter">
                            <c:set var="title" value="${item.key}"/>
                            <tr>
                                <td>${counter.count}</td>
                                <td>${title}</td>
                                <td><input type="checkbox" name="chkRemove" value="${title}"/></td>
                            </tr>
                        </c:forEach>
                            <tr>
                                <c:url var="add" value="CenterServlet">
                                    <c:param name="btAction" value="add"/>
                                </c:url>
                                <td colspan="2"><a href="${add}">Add More Cart</a> </td>
                                <td><input type="submit" value="Remove" name="btAction" /></td>
                            </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
    </body>
</html>
