<%-- 
    Document   : cart
    Created on : Mar 24, 2015, 8:49:06 AM
    Author     : Suzy
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="sample.obj.CartObj"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Your Cart</title>
    </head>
    <body>

        <c:if test="${not empty USER}">
            <font color="red">Welcome, ${sessionScope.USER}</font>
        </c:if>

        <h1>Your Cart including</h1>

        <c:if test="${not empty CART}">
            <c:set var="items" value="${CART.items}"/>
            <c:if test="${not empty items}">
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
                    <c:forEach var="item" items="${items}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${item.key}</td>
                            <td>${item.value}</td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${item.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"><a href="bookStore.html">Add More Cart</a> </td>
                        <td><input type="submit" value="Remove Item" name="btAction" /></td>
                    </tr>
                </form>
            </tbody>
        </table>
            </c:if>
            <c:if test="${empty items}">
                <h2>Your Cart is empty!</h2>
                Click <a href="bookStore.html">here</a> to buy more!!!
            </c:if>
    </c:if>
        
    <%--
        session = request.getSession(false);
        if (session != null) {
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
    %>
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
            <%
                HashMap items = cart.getItems();
                int count = 0;
                Iterator iter = items.entrySet().iterator();
            %>
        <form action="CenterServlet">
            <%
                while (iter.hasNext()) {
                    Map.Entry item = (Map.Entry) iter.next();
            %>
            <tr>
                <td><%= ++count%></td>
                <td><%= item.getKey()%></td>
                <td><%= item.getValue()%></td>
                <td><input type="checkbox" name="chkItem" value="<%= item.getKey()%>" /></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="3"><a href="bookStore.html">Add More Cart</a> </td>
                <td><input type="submit" value="Remove Item" name="btAction" /> </td>
            </tr>
        </form>
        <%
        %>
    </tbody>
</table>

    <%                }
        }
    --%>
</body>
</html>
