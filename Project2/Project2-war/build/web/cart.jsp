<%-- 
    Document   : cart
    Created on : Mar 27, 2015, 9:53:04 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Details</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${not empty user}">
            <font color="red">Welcome, ${user}</font>
        </c:if>
        <h1>Your Cart including</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.items}"/>
        <c:set var="errors" value="${sessionScope.QTYERROR}"/>
        <c:if test="${not empty cart and not empty items}">
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
                    <c:forEach var="item" items="${items}" varStatus="counter">
                        <c:set var="dto" value="${item.key}"/>
                        <c:if test="${not empty errors and not empty errors[dto]}">
                            <tr style="color: red">
                            </c:if>
                            <c:if test="${empty errors or not empty errors[dto]}">
                            <tr>
                            </c:if>
                            <td>${counter.count}</td>
                            <td>${dto.productName}</td>
                            <td>${dto.unit}</td>
                            <td>${item.value}</td>
                            <td>${dto.price}</td>
                            <td>${dto.price * item.value}</td>
                            <td><input type="checkbox" name="chkRemove" value="${dto.productID}" /></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4"></td>
                        <td>Total</td>
                        <td>${cart.total}</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <c:url var="searchPage" value="CenterServlet">
                                <c:param name="btAction" value="Search"/>
                                <c:param name="txtSearch" value="${param.lastSearchValue}"/>
                            </c:url>
                            <a href="${searchPage}">Add More Cart</a>
                        </td>
                        <c:if test="${empty user}">
                            <c:url var="loginPage" value="CenterServlet">
                                <c:param name="btAction" value="LoginPage"/>
                            </c:url>
                            <td colspan="2">
                                <a href="${loginPage}">Checkout</a>
                            </td>
                        </c:if>
                        <c:if test="${not empty user and empty errors}">
                            <c:url var="confirm" value="CenterServlet">
                                <c:param name="btAction" value="Confirm"/>
                            </c:url>
                            <td colspan="2">
                                <a href="${confirm}">Checkout</a>
                            </td>
                        </c:if>
                        <c:if test="${not empty user and not empty errors}">
                            <c:url var="cartPage" value="CenterServlet">
                                <c:param name="btAction" value="View Cart"/>
                            </c:url>
                            <td colspan="2">
                                <a href="${cartPage}">Checkout</a>
                            </td>
                        </c:if>
                        <td>
                            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cart or empty items}">
        <c:url var="searchPage" value="CenterServlet">
            <c:param name="btAction" value="Search"/>
            <c:param name="txtSearch" value="${param.lastSearchValue}"/>
        </c:url>
        <h3>Your Cart is empty!!! Click <a href="${searchPage}">here</a> to buy product!!!<h3>
            </c:if>

            <c:if test="${not empty errors}">
                <c:forEach var="dtoErr" items="${errors}">
                    <c:set var="dto" value="${dtoErr.key}"/>
                    <c:set var="qty" value="${dtoErr.value}"/>
                    <h4><font color="red">There are only ${qty} product ${dto.productName} in warehouse!!!</font></h4>
                    </c:forEach>
                <h3>Do you want to buy all of them?</h3>
                <form action="CenterServlet">
                    <input type="submit" value="Yes" name="btAction" />
                    <input type="submit" value="No" name="btAction" />
                </form>
            </c:if>
            </body>
            </html>
