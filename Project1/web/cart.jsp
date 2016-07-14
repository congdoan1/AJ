<%-- 
    Document   : cart
    Created on : Mar 24, 2015, 12:30:09 AM
    Author     : Suzy
--%>

<%@page import="sample.customer.CustomerDTO"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="sample.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Your Shopping Cart</title>
    </head>
    <body>
        <%
            String customer = (String) session.getAttribute("CUSTOMER");
            if (customer != null) {
        %>
        <font color="red">Welcome, <%= customer%></font>
        <%
            }
        %>
        <h1>Your Cart including</h1>
        <%
            String searchValue = request.getParameter("lastSearchValue");
            session = request.getSession(false);
            if (session != null) {
                HashMap<ProductDTO, Integer> errors = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart == null || cart.getItems().isEmpty()) {
                    String urlRewritting = "CenterServlet?btAction=Search&txtSearch=" + searchValue;
        %>
        <h2>Your Cart is empty!! Click <a href="<%= urlRewritting%>">here</a> to buy product!</h2>
        <%
        } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product</th>
                    <th>Unit</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="CenterServlet">
                <%
                    HashMap items = cart.getItems();
                    int count = 0;
                    float total = 0;
                    Iterator iter = items.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        ProductDTO product = (ProductDTO) item.getKey();
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= product.getProductName()%></td>
                    <td><%= product.getUnit()%></td>
                    <td><%= item.getValue()%></td>
                    <td><%= product.getPrice()%></td>
                    <td>
                        <%= product.getPrice() * Integer.parseInt(item.getValue().toString())%>
                        <input type="hidden" name="txtTotal" value="<%= total%>" />
                    </td>
                    <td><input type="checkbox" name="chkRemove" value="<%= product.getProductID()%>" /></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="4"></td>
                    <td>Total</td>
                    <td><%= cart.getTotal()%></td>
                </tr>
                <tr>
                    <%
                        String urlRewritting = "CenterServlet?btAction=Search&txtSearch=" + searchValue;
                    %>
                    <td colspan="4"><a href="<%= urlRewritting%>">Add More Cart</a></td>
                    <%
                        if (customer != null && errors == null) {
                            urlRewritting = "CenterServlet?btAction=Checkout";
                    %>
                    <td colspan="2"><a href="<%= urlRewritting %>">Check out</a></td>
                    <%
                    } else if (errors != null) {
                    %>
                    <td colspan="2"><a href="CenterServlet?btAction=ViewCart">Check out</a></td>
                    <%
                    } else {
                    %>
                    <td colspan="2"><a href="CenterServlet?btAction=LoginPage">Check out</a></td>
                    <%
                        }
                    %>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                        <input type="submit" value="Remove" name="btAction" /> 
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
    <%
        }
        //HashMap<ProductDTO, Integer> errors = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
        if (errors != null) {
            Iterator iter = errors.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry item = (Map.Entry) iter.next();
                ProductDTO dto = (ProductDTO) item.getKey();
    %>
    <h4><font color="red">There are only <%= item.getValue()%> product <%= dto.getProductName()%> in warehouse!!!</font></h4>
        <%
            }
        %>
    <h3>Do you want to buy all of them?</h3>
    <form action="CenterServlet">
        <input type="submit" value="Yes" name="btAction" />
        <input type="submit" value="No" name="btAction" />
    </form>    
    <%
            }
        }
    %>
</body>
</html>
