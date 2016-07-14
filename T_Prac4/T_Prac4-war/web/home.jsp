<%-- 
    Document   : home
    Created on : Apr 3, 2015, 8:24:23 AM
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
            From <input type="text" name="txtFrom" value="${param.txtFrom}" /><br/>
            <c:if test="${not empty requestScope.dFromErr}">
                <font color="red">${requestScope.dFromErr}</font><br/>
            </c:if>
            To <input type="text" name="txtTo" value="${param.txtTo}" /><br/>
            <c:if test="${not empty requestScope.dToErr}">
                <font color="red">${requestScope.dToErr}</font><br/>
            </c:if>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="list" value="${requestScope.RESULT}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>DOB</th>
                        <th>Place Of Birth</th>
                        <th>Weight</th>
                        <th>Height</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ch" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${ch.name}
                                <input type="hidden" name="txtName" value="${ch.name}" />
                            </td>
                            <td>
                                <input type="text" name="txtDOB" value="${ch.dateOfBirth}" />
                            </td>
                            <td>
                                <input type="text" name="txtPlaceOfBirth" value="${ch.placeOfBirth}" />
                            </td>
                            <td>
                                <input type="text" name="txtWeight" value="${ch.weight}" />
                            </td>
                            <td>
                                <input type="text" name="txtHeight" value="${ch.height}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtCID" value="${ch.CID}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="txtCID" value="${ch.CID}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty requestScope.deleteRes}">
            <font color="red">${requestScope.deleteRes}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.updateRes}">
            <font color="red">${requestScope.updateRes}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.dobErr}">
            <font color="red">${requestScope.dobErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.pobErr}">
            <font color="red">${requestScope.pobErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.weightErr}">
            <font color="red">${requestScope.weightErr}</font><br/>
        </c:if>
        <c:if test="${not empty requestScope.heightErr}">
            <font color="red">${requestScope.heightErr}</font><br/>
        </c:if>
    </c:if>
</body>
</html>
