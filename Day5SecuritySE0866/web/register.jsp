<%-- 
    Document   : register
    Created on : Mar 23, 2015, 4:29:44 PM
    Author     : Suzy
--%>

<%@page import="sample.registration.RegistrationErrors"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register New Account</h1>
        <form action="CenterServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(chars: 6-20)<br/>
            <%
                RegistrationErrors errors = (RegistrationErrors) request.getAttribute("ERROR");
                if (errors != null) {
                    if (errors.getUsernameErr() != null) {
            %>
            <font color="red"><%= errors.getUsernameErr()%></font><br/>
            <%
                    }
                }
            %>
            Password* <input type="password" name="txtPassword" value="" />(chars: 6-30)<br/>
            <%
                if (errors != null) {
                    if (errors.getPasswordErr() != null) {
            %>
            <font color="red"><%= errors.getPasswordErr()%></font><br/>
            <%
                    }
                }
            %>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <%
                if (errors != null) {
                    if (errors.getConfirmNotMatch() != null) {
            %>
            <font color="red"><%= errors.getConfirmNotMatch()%></font><br/>
            <%
                    }
                }
            %>
            Lastname* <input type="text" name="txtLastname" value="" />(chars: 2-40)<br/>
            <%
                if (errors != null) {
                    if (errors.getLastnameErr() != null) {
            %>
            <font color="red"><%= errors.getLastnameErr()%></font><br/>
            <%
                    }
                }
            %>
            <input type="submit" value="Create New User" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <%
            if (errors != null) {
                if (errors.getUsernameDup() != null) {
        %>
        <font color="red"><%= errors.getUsernameDup()%></font><br/>
        <%
                }
            }
        %>
    </body>
</html>
