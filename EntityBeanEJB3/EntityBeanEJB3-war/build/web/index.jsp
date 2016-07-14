<%-- 
    Document   : index
    Created on : Mar 29, 2015, 11:35:55 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="CenterServlet" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            Lastname <input type="text" name="txtLastname" value="" /><br/>
            Admin <input type="checkbox" name="chkAdmin" value="Admin" /><br/>
            <input type="submit" value="Add" name="btAction" />
        </form>
    </body>
</html>
