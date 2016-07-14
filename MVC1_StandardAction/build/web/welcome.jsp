<%-- 
    Document   : welcome
    Created on : Mar 24, 2015, 3:08:09 PM
    Author     : Suzy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page import="sample.bean.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <jsp:useBean id="loginBean" class="sample.bean.LoginBean" scope="session"/>
        <font color="red">
        Welcome, <jsp:getProperty name="loginBean" property="username"/><br/></font>
        Welcome, <%= ((LoginBean) session.getAttribute("loginBean")).getUsername()%><br/>
        <font color="green">
        Welcome, <%= loginBean.getUsername()%></font><br/>
        <font color="purple">Welcome, ${sessionScope.loginBean.username}</font><br/>
        <font color="orange">Welcome, ${loginBean.username}</font>
        <h1>Welcome to Standard Action</h1>

        <a href="StandardServlet">Test Action</a><br/>
        Param 1: ${param.param1} <br/>
        Param 2: ${param.param2} <br/>
        <%--Param 1: <%= request.getParameter("param1")%><br/>
        Param 2: <%= request.getParameter("param2")%><br/>--%>
        <form action="welcome.jsp">
            Name <input type="text" name="txtSearch" value="" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="name" value="${param.txtSearch}" />
        <c:if test="${not empty param.txtSearch}" >
            <c:catch var="ex">
                <sql:setDataSource var="con" dataSource="DBCon" />
                <c:if test="${not empty con}">
                    <sql:query var="rs" dataSource="${con}" >
                        SELECT * FROM Registration WHERE lastname LIKE ?
                        <sql:param value="%${param.txtSearch}%"/>
                    </sql:query>
                    <c:if test="${not empty rs}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                        <c:forEach var="colName" items="${rs.columnNames}" >
                                        <th>${colName}</th>
                                        </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="rows" items="${rs.rowsByIndex}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <c:forEach var="field" items="${rows}">
                                            <td>${field}</td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>
            </c:catch>
            <c:if test="${not empty ex}">
                <font color="red">Error: ${ex}</font>
            </c:if>
        </c:if>

    </body>
</html>
