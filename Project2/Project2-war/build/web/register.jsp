<%-- 
    Document   : register
    Created on : Mar 24, 2015, 10:40:59 AM
    Author     : Suzy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="CenterServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6-15 characters)<br/>
            <c:set var="errors" value="${requestScope.ERROR}"/>
            <c:if test="${not empty errors and not empty errors.usernameErr}">
                <font color="red">${errors.usernameErr}</font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(8-30 characters)<br/>
            <c:if test="${not empty errors and not empty errors.passwordErr}">
                <font color="red">${errors.passwordErr}</font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors and not empty errors.confirmNotMatch}">
                <font color="red">${errors.confirmNotMatch}</font><br/>
            </c:if>
            Customer Name <input type="text" name="txtCustName" value="${param.txtCustName}" />(2-15 characters)<br/>
            <c:if test="${not empty errors and not empty errors.custNameErr}">
                <font color="red">${errors.custNameErr}</font><br/>
            </c:if>
            Lastname <input type="text" name="txtLastname" value="${param.txtLastname}" />(2-15 characters)<br/>
            <c:if test="${not empty errors and not empty errors.lastnameErr}">
                <font color="red">${errors.lastnameErr}</font><br/>
            </c:if>
            Middle Name <input type="text" name="txtMiddleName" value="${param.txtMiddleName}" /><br/>
            Address <input type="text" name="txtAddress" value="${param.txtAddress}" /><br/>
            Phone <input type="text" name="txtPhone" value="${param.txtPhone}" /><br/>
            <c:if test="${not empty errors and not empty errors.phoneErr}">
                <font color="red">${errors.phoneErr}</font><br/>
            </c:if>
            <input type="submit" value="Create" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
        <c:if test="${not empty errors and not empty errors.usernameDup}">
            <font color="red">${errors.usernameDup}</font><br/>
        </c:if>
    </body>
</html>
