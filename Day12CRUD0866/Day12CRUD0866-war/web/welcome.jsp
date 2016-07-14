<%-- 
    Document   : welcome
    Created on : Mar 31, 2015, 3:25:04 PM
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
        <h1>Welcome to Entity Bean</h1>
        <form action="CenterServlet">
            Search <input type="text" name="txtSearch" value="" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Lastname</th>
                        <th>Roles</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reg" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${reg.username}
                                <input type="hidden" name="txtUsername" value="${reg.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${reg.password}" />
                            </td>
                            <td>${reg.lastname}</td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ADMIn" 
                                       <c:if test="${reg.isAdmin}">
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtUsername" value="${reg.username}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearch}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="lastSearchValue" value="${param.txtSearch}" />
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
