<%-- 
    Document   : welcome
    Created on : Apr 2, 2015, 8:34:23 AM
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
        <h1>Hihi Hehe!!!</h1>
        <form action="CenterServlet">
            From <input type="text" name="txtFrom" value="${param.txtFrom}" /><br/>
            To <input type="text" name="txtTo" value="${param.txtTo}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Enrollment</th>
                        <th>Fullname</th>
                        <th>Sex</th>
                        <th>Year Of Birth</th>
                        <th>Place Of Birth</th>
                        <th>Password</th>
                        <th>GPA</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="stu" items="${list}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${stu.enrollment}</td>
                            <td>${stu.fullname}</td>
                            <td>
                                <c:if test="${stu.sex}">
                                    Male
                                </c:if>
                                <c:if test="${not stu.sex}">
                                    Female
                                </c:if>
                            </td>
                            <td>${stu.yearOfBirth}</td>
                            <td>${stu.placeOfBirth}</td>
                            <td>${stu.password}</td>
                            <td>${stu.gpa}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
