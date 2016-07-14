<%-- 
    Document   : index
    Created on : Mar 22, 2015, 8:35:04 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="CenterServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            <c:set var="errors" value="${requestScope.ERROR}"/>
            <c:if test="${not empty errors.usernameErr}">
                <font color="red">Vui long nhap tai khoan!!!</font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty errors.passwordErr}">
                <font color="red">Vui long nhap mat khau!!!</font><br/>
            </c:if>
            <input type="submit" value="Login" name="btAction"/>
        </form><br/>
        
        <%--<c:set var="username" value="${param.txtUsername}"/>
        <c:set var="password" value="${param.txtPassword}"/>
        <c:if test="${not empty username and not empty password}">
                <c:catch var="ex">
                    <sql:setDataSource var="con" dataSource="DBCon"/>
                    <c:if test="${not empty con}">
                        <sql:query var="rs" dataSource="${con}">
                            SELECT Username FROM TBL_Account WHERE Username = ? AND Password = ?
                            <sql:param value="${username}"/>
                            <sql:param value="${password}"/>
                        </sql:query>
                        <c:choose>
                            <c:when test="${rs.rowCount > 0}">
                                <c:set var="USER" value="${username}" scope="session"/>
                                <c:redirect url="view.jsp"></c:redirect>
                            </c:when>
                            <c:otherwise>
                                <c:redirect url="error.jsp"></c:redirect>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:catch>
                <c:if test="${not empty ex}">
                    <font color="red">Error: ${ex}</font>
                </c:if>
        </c:if>--%>
    </body>
</html>
