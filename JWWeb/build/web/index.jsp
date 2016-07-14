<%-- 
    Document   : index
    Created on : Mar 30, 2015, 4:46:38 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Calculator</h1>
        <form action="CalculatorServlet">
            Num 1 <input type="text" name="txtNum1" value="" /><br/>
            Num 2 <input type="text" name="txtNum2" value="" /><br/>
            <input type="submit" value="Add" name="btAction" />
            <input type="submit" value="Subtract" name="btAction" />
        </form>
    </body>
</html>
