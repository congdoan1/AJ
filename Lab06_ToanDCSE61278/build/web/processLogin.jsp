<%-- 
    Document   : processLogin
    Created on : Mar 24, 2015, 6:20:29 PM
    Author     : Suzy
--%>


<%@page import="sample.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Processing</title>
    </head>
    <body>
        <h1>Processing...</h1>
        <jsp:useBean id="loginBean" class="sample.bean.LoginBean" scope="session" />
        <jsp:setProperty name="loginBean" property="*" />
        <%
            AccountDTO dto = loginBean.checkLogin();
            if (dto != null) {
                loginBean.setRoles(dto.isRoles());
        %>
        <jsp:forward page="view.jsp"></jsp:forward>
        <%
            }
        %>
        <h2>Invalid username or password!!!!!</h2>
        <h4>Click <a href="index.jsp">here</a>  to try again!!!</h4>
    </body>
</html>
