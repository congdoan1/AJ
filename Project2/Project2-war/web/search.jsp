<%-- 
    Document   : search
    Created on : Mar 27, 2015, 9:24:45 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${not empty user}">
            <font color="red">Welcome, ${user}</font>
        </c:if>
        <h1>Search Product</h1>
        <form action="CenterServlet">
            Product Name <input type="text" name="txtSearch" value="${param.txtSearch}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:set var="result" value="${requestScope.RESULT}"/>
        <c:if test="${not empty result}">
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
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${dto.productName}
                                <input type="hidden" name="txtProductName" value="${dto.productName}" /> 
                            </td>
                            <td>
                                ${dto.unit}
                                <input type="hidden" name="txtUnit" value="${dto.unit}" />
                            </td>
                            <td><input type="text" name="txtQuantity" value="" /></td>
                            <td>
                                ${dto.price}
                                <input type="hidden" name="txtPrice" value="${dto.price}" />
                            </td>
                            <td>
                                <input type="hidden" name="txtProductID" value="${dto.productID}" />
                                <input type="hidden" name="lastSearchValue" value="${param.txtSearch}" />
                                <input type="submit" value="Add To Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty result and not empty param.txtSearch}">
        <h2><font color="red">No product matched!!!</font></h2><br/>
        </c:if>

    <c:set var="errors" value="${requestScope.ERROR}"/>
    <c:if test="${not empty errors}">
        <c:if test="${not empty errors.qtyEmpty}">
            <font color="red">${errors.qtyEmpty}</font><br/>
        </c:if>
        <c:if test="${not empty errors.qtyErr}">
            <font color="red">${errors.qtyErr}</font><br/>
        </c:if>
    </c:if>
    <c:url var="cartPage" value="CenterServlet">
        <c:param name="btAction" value="View Cart"/>
        <c:param name="lastSearchValue" value="${param.txtSearch}"/>
    </c:url>
    <a href="${cartPage}">View Cart</a>
</body>
</html>
