<%-- 
    Document   : register
    Created on : Mar 24, 2015, 10:40:59 AM
    Author     : Suzy
--%>

<%@page import="sample.customer.CustomerErrors"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="CenterServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(6-15 characters)<br/>
            <%
                CustomerErrors errors = (CustomerErrors) request.getAttribute("ERROR");
                if (errors != null) {
                    if (errors.getUsernameErr() != null) {
            %>
            <font color="red"><%= errors.getUsernameErr()%></font><br/>
            <%
                    }
                }
            %>
            Password* <input type="password" name="txtPassword" value="" />(8-30 characters)<br/>
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
            <% if (errors != null) {
                    if (errors.getConfirmNotMatch() != null) {
            %>
            <font color="red"><%= errors.getConfirmNotMatch()%></font><br/>
            <%
                    }
                }
            %>
            Customer Name <input type="text" name="txtCustName" value="" />(2-15 characters)<br/>
            <% if (errors != null) {
                    if (errors.getCustNameErr() != null) {
            %>
            <font color="red"><%= errors.getCustNameErr()%></font><br/>
            <%
                    }
                }
            %>
            Lastname <input type="text" name="txtLastname" value="" />(2-15 characters)<br/>
            <%
                if (errors != null) {
                    if (errors.getLastnameErr() != null) {
            %>
            <font color="red"><%= errors.getLastnameErr()%></font><br/>
            <%
                    }
                }
            %>
            Middle Name <input type="text" name="txtMiddleName" value="" /><br/>
            Address <input type="text" name="txtAddress" value="" /><br/>
            Phone <input type="text" name="txtPhone" value="" /><br/>
            <%             if (errors != null) {
                    if (errors.getPhoneErr() != null) {
            %>
            <font color="red"><%= errors.getPhoneErr()%></font><br/>
            <%
                    }
                }
            %>
            <input type="submit" value="Create" name="btAction" />
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
