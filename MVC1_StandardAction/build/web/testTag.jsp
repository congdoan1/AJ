<%-- 
    Document   : testTag
    Created on : Mar 25, 2015, 3:48:05 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/myTaglib.tld" prefix="mine"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="toan"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Page</title>
    </head>
    <body>
        <h1>Test</h1>
        <h2>Classic Empty</h2>
        <mine:ClassicEmptyTag/><br/>
        Sau khi da in mine<br/>
        <h2>Tag Attr</h2>
        Thuoc tinh co tri dung <mine:ClassicEmptyAttrTag count="10" /><br/>
        Thuoc tinh ko co tri <mine:ClassicEmptyAttrTag count="" /><br/>
        Khong co thuoc tinh <mine:ClassicEmptyAttrTag/><br/>
        Thuoc tinh co tri sao <mine:ClassicEmptyAttrTag count="toan"/><br/>
        
        <toan:datagrid datasource="DBCon" queryString="SELECT * FROM Registration WHERE username = ? AND lastname = ?"
                       hde="duy" efg="duydt"/>
    </body>
</html>
