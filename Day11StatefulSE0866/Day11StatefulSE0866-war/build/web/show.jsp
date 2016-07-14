<%-- 
    Document   : show
    Created on : Mar 30, 2015, 3:30:28 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Page</title>
    </head>
    <body>
        <h1>Your Shopping Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${empty cart}">
            Chuc mung quy khach vi quy khach da bi tuoc mat gio hang, hahaha!!!
        </c:if>
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
                    <c:forEach var="item" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${item.key}</td>
                            <td>${item.value}</td>
                            <td>
                                <input type="checkbox" name="chkRemove" value="${item.key}" />
                            </td>
                        </tr>

                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="CenterServlet?btAction=Add More">Add More Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
</body>
</html>
