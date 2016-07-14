<%-- 
    Document   : cart
    Created on : Mar 24, 2015, 8:49:06 AM
    Author     : Suzy
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="sample.obj.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Your Cart</title>
    </head>
    <body>
        <%
            String username = (String) session.getAttribute("USER");
            if (username != null) {
        %>
        <font color="red">Welcome, <%= session.getAttribute("USER")%></font>
        <%
            }
        %>
        <h1>Your Cart including</h1>
        <%
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
    %>
</body>
</html>
