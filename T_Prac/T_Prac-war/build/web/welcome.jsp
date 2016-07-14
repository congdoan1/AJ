<%-- 
    Document   : welcome
    Created on : Apr 2, 2015, 1:32:03 AM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <h1>Welcome to Prac</h1>
        <form action="CenterServlet">
            <input type="submit" value="List" name="btAction" />
        </form>
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
                    <c:forEach var="elec" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${elec.electid}
                                <input type="hidden" name="txtElectID" value="${elec.electid}" />
                            </td>
                            <td>
                                <input type="text" name="txtDistributor" value="${elec.distributor}" />
                            </td>
                            <td>
                                <input type="text" name="txtVoltage" value="${elec.voltage}" />
                            </td>
                            <td>
                                <input type="text" name="txtPower" value="${elec.power}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtElectID" value="${elec.electid}"/>
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
        <c:if test="${not empty requestScope.distributorErr}">
            <font color="red">${requestScope.distributorErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.voltageErr}">
            <font color="red">${requestScope.voltageErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.voltageFor}">
            <font color="red">${requestScope.voltageFor}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.powerErr}">
            <font color="red">${requestScope.powerErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.powerFor}">
            <font color="red">${requestScope.powerFor}</font><br/>
        </c:if>
    </c:if>
</body>
</html>
