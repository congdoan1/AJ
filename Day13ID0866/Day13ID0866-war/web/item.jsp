<%-- 
    Document   : item
    Created on : Apr 1, 2015, 3:23:40 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Manager</title>
    </head>
    <body>
        <h1>Insert Item</h1>
        <form action="CenterServlet">
            Item ID <input type="text" name="txtItemID" value="" /><br/>
            <c:if test="${not empty requestScope.itemIDErr}">
                <font color="red">${requestScope.itemIDErr}</font><br/>
            </c:if>
            Description <input type="text" name="txtDesc" value="" /><br/>
            <c:if test="${not empty requestScope.descErr}">
                <font color="red">${requestScope.descErr}</font><br/>
            </c:if>
            Amount <input type="text" name="txtAmount" value="" /><br/>
            <c:if test="${not empty requestScope.amtErr}">
                <font color="red">${requestScope.amtErr}</font><br/>
            </c:if>
            <input type="submit" value="Insert Item" name="btAction" />
        </form><br/><br/>
        <c:if test="${not empty requestScope.itemDup}">
            <font color="red">${requestScope.itemDup}</font><br/>
        </c:if>

        <form action="CenterServlet">
            From <input type="text" name="txtFrom" value="${param.txtFrom}" /><br/>
            To <input type="text" name="txtTo" value="${param.txtTo}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="items" value="${requestScope.LIST}"/>
        <c:if test="${not empty items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Item ID</th>
                        <th>DateS</th>
                        <th>Description</th>
                        <th>Amt</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${item.itemPK.itemID}
                                <input type="hidden" name="txtItemID" value="${item.itemPK.itemID}" />
                            </td>
                            <td>
                                ${item.itemPK.dateS}
                                <input type="hidden" name="txtDateS" value="${item.itemPK.dateS}" />
                            </td>
                            <td>
                                <input type="text" name="txtDesc" value="${item.description}" />
                            </td>
                            <td>
                                <input type="text" name="txtAmt" value="${item.amt}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="itemID" value="${item.itemPK.itemID}"/>
                                    <c:param name="dateS" value="${item.itemPK.dateS}"/>
                                    <c:param name="lastFrom" value="${param.txtFrom}"/>
                                    <c:param name="lastTo" value="${param.txtTo}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="lastFrom" value="${param.txtFrom}" />
                                <input type="hidden" name="lastTo" value="${param.txtTo}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
