<%-- 
    Document   : show
    Created on : Mar 22, 2015, 9:09:01 PM
    Author     : Suzy
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="toan"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <font color="red">Welcome ${sessionScope.USER}</font>
        <h1>Information Page</h1>
        <form action="view.jsp">
            Lastname <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <input type="submit" value="Search" name="btAction" /><br/>
        </form><br/>
        <c:set var="name" value="${param.txtSearch}" />
        <c:if test="${not empty name}">
            <c:catch var="ex">
                <sql:setDataSource var="con" dataSource="DBCon"/>
                <c:if test="${not empty con}">
                    <sql:query var="rs" dataSource="${con}">
                        SELECT Username FROM TBL_Account WHERE Lastname LIKE ?
                        <sql:param value="%${name}%"/>
                    </sql:query>
                        <c:if test="${rs.rowCount > 0}">
                            <h2>Result of Searching</h2>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <c:forEach var="colName" items="${rs.columnNames}">
                                    <th>${colName}</th>
                                    </c:forEach>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="rows" items="${rs.rowsByIndex}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <c:forEach var="field" items="${rows}">
                                            <td>${field}</td>
                                        </c:forEach>
                                        <td><a href="">ViewDetail</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${rs.rowCount <= 0}">
                        <h2>No record matched!!!</h2>
                    </c:if>
                </c:if>
            </c:catch>
            <c:if test="${not empty ex}">
                <font color="red">Error: ${ex}</font>
            </c:if>
        </c:if>
        <c:if test="${empty name}">
            <toan:dataGrid url="jdbc:sqlserver://localhost:1433;databaseName=UserDB" user="sa" pass="" sql="SELECT Username FROM TBL_Account"/>
        </c:if>
        <%--
            String name = request.getParameter("txtSearch");
            HashMap<String, AccountDTO> result = null;
            if (name == null) {
                name = "";
                response.sendRedirect("CenterServlet?btAction=Search&txtSearch=");
            } else {
                result = (HashMap<String, AccountDTO>) session.getAttribute("RESULT");
            }
            if (result != null) {
        %>
        <h1>Result of Searching <%= name%></h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    Iterator iter = result.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry userSearch = (Map.Entry) iter.next();
                        String usernameSearch = (String) userSearch.getKey();
                        String urlRewritting = "CenterServlet?"
                                + "btAction=ViewDetails&txtUsername="
                                + usernameSearch
                                + "&lastSearchValue="
                                + name;
                %>
            <form action="CenterServlet">
                <tr>
                    <td><%= ++count%></td>
                    <td>
                        <%= usernameSearch%>
                    </td>
                    <td>
                        <a href="<%= urlRewritting%>">ViewDetail</a>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <h3>No Record!!!</h3>
        <%
            }
        --%>
    </body>
</html>
