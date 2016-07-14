<%-- 
    Document   : processLogin
    Created on : Mar 24, 2015, 2:47:38 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Processing</title>
    </head>
    <body>
        <h1>Processing...</h1>
        <jsp:useBean id="loginBean" class="sample.bean.LoginBean" scope="session"/>

        <%--<jsp:setProperty name="loginBean" property="username"
        value='<%= request.getParameter("txtUsername") %>' />
        <jsp:setProperty name="loginBean" property="password"
        value='<%= request.getParameter("txtPassword") %>' />--%>

        <%--<jsp:setProperty name="loginBean" property="username" param="txtUsername"/>
        <jsp:setProperty name="loginBean" property="password" param="txtPassword"/>--%>

        <%--<jsp:setProperty name="loginBean" property="username" />--%>

        <jsp:setProperty name="loginBean" property="*" />
        <%--Results:<br/>
        Username: <jsp:getProperty name="loginBean" property="username" /><br/>
        Password: <jsp:getProperty name="loginBean" property="password" /><br/>--%>
        <%--
            if (loginBean.checkLogin()) {
                response.sendRedirect("welcome.jsp");
            } else {
        %>
        <h1><font color="red">Invalid username or password</font></h1>
            <%
                }
        --%>

        <%--
            if (loginBean.checkLogin()) {
        %>
        <jsp:forward page="welcome.jsp" >
            <jsp:param name="param1" value="Standard Action" />
            <jsp:param name="param2" value="Forward" />
        </jsp:forward>    
        <%
            }
        %>
        <h2><font color="red">Invalid username or password</font></h2>
        <%
            System.out.println("Da chay den day");
        --%>

        <c:if test="${loginBean.checkLogin()}">
            <jsp:forward page="welcome.jsp" >
                <jsp:param name="param1" value="Standard Action" />
                <jsp:param name="param2" value="Forward" />
            </jsp:forward>
        </c:if>
        <h2><font color="red">Invalid username or password</font></h2>
    </body>
</html>
