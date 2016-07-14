<%-- 
    Document   : index
    Created on : Mar 22, 2015, 5:49:11 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>MVC Shopping Cart Demo!</h1>
        <form action="CenterServlet">
            Please choose your favorite book:<br/>
            <select name="cboBook" size="20">
                <option>Common Gateway Interface - CGI</option>
                <option>Servlet</option>
                <option>JavaServer Page - JSP</option>
                <option>Tomcat Server</option>
                <option>Struts</option>
                <option>JavaServer Faces - JSP</option>
                <option>Integrating Java with XML - IXJ</option>
                <option>Java Web Service - JWS</option>
                <option>Enteprise Java Beans - EJB</option>
                <option>JBoss Server</option>
                <option>Grassfish Server</option>
            </select><br/>
            <input type="submit" value="Add To Cart" name="btAction" />
            <input type="submit" value="View Cart" name="btAction" />
        </form>
    </body>
</html>
