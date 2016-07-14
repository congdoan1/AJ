<%-- 
    Document   : home
    Created on : Apr 3, 2015, 1:53:36 AM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <form action="CenterServlet">
            <input type="submit" value="List" name="btAction" />
        </form><br/>
        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Electric ID</th>
                        <th>Distributor</th>
                        <th>Voltage</th>
                        <th>Power</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="el" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${el.electid}
                                <input type="hidden" name="txtElectID" value="${el.electid}" />
                            </td>
                            <td>
                                <input type="text" name="txtDistributor" value="${el.distributor}" />
                            </td>
                            <td>
                                <input type="text" name="txtVoltage" value="${el.voltage}" />
                            </td>
                            <td>
                                <input type="text" name="txtPower" value="${el.power}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtElectID" value="${el.electid}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty requestScope.deleteErr}">
            <font color="red">${requestScope.deleteErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.distributorErr}">
            <font color="red">${requestScope.distributorErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.voltageErr}">
            <font color="red">${requestScope.voltageErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.powerErr}">
            <font color="red">${requestScope.powerErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.updateErr}">
            <font color="red">${requestScope.updateErr}</font><br/>
        </c:if>
    </c:if>
</body>
</html>
