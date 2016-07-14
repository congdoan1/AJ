<%-- 
    Document   : search
    Created on : Mar 23, 2015, 9:59:57 PM
    Author     : Suzy
--%>

<%@page import="sample.product.ProductErrors"%>
<%@page import="sample.customer.CustomerDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
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
        <h1>Search Product</h1>
        <form action="CenterServlet">
            Product Name <input type="text" name="txtSearch" value="" /><br/><br/>
            <input type="submit" value="Search" name="btAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <%
            List<ProductDTO> result = (List<ProductDTO>) session.getAttribute("RESULT");
            if (result == null && request.getParameter("txtSearch") != null && !((String) request.getParameter("txtSearch")).equals("null")) {
        %>
        <h2>No product matched!!!<h2>
                <%
                } else if (result != null) {
                %>
                <h3>Result</h3>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product</th>
                            <th>Unit</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < result.size(); i++) {
                                ProductDTO product = result.get(i);
                        %>
                    <form action="CenterServlet">
                        <tr>
                            <td><%= (i + 1)%></td>
                            <td>
                                <%= product.getProductName()%>
                                <input type="hidden" name="txtProductName" value="<%= product.getProductName()%>" />
                            </td>
                            <td>
                                <%= product.getUnit()%>
                                <input type="hidden" name="txtUnit" value="<%= product.getUnit()%>" />
                            </td>
                            <td><input type="text" name="txtQuantity" value="" /> </td>
                            <td>
                                <%= product.getPrice()%>
                                <input type="hidden" name="txtPrice" value="<%= product.getPrice()%>" />
                            </td>
                            <td>
                                <input type="hidden" name="txtProductID" value="<%= product.getProductID()%>" />
                                <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearch")%>" />
                                <input type="submit" value="Add To Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <%
                    }
                    ProductErrors errors = (ProductErrors) request.getAttribute("ERROR");
                    if (errors != null) {
                        if (errors.getQtyEmpty() != null) {
                %>
                <font color="red"><%= errors.getQtyEmpty()%></font><br/>
                <%
                    }
                    if (errors.getQtyErr() != null) {
                %>
                <font color="red"><%= errors.getQtyErr()%></font><br/>
                <%
                        }
                    }
                    String urlRewritting = "CenterServlet?btAction=ViewCart&lastSearchValue=" + request.getParameter("txtSearch");
                %>
                <br/>
                <a href="<%= urlRewritting%>">View Cart</a>
                </body>
                </html>
