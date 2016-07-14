<%-- 
    Document   : index
    Created on : Mar 22, 2015, 8:35:04 PM
    Author     : Suzy
--%>

<%@page import="sample.account.AccountErrors"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="CenterServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            <%
                AccountErrors errors = (AccountErrors) request.getAttribute("ERROR");
                if (errors != null) {
                    if (errors.getUsernameErr() != null) {
            %>
            <font color="red"><%= errors.getUsernameErr()%></font><br/>
            <%
                    }
                }
            %>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <%
                if (errors != null) {
                    if (errors.getPasswordErr()!= null) {
            %>
            <font color="red"><%= errors.getPasswordErr()%></font><br/>
            <%
                    }
                }
            %>
            <input type="submit" value="Login" name="btAction" />
        </form>
    </body>
</html>
