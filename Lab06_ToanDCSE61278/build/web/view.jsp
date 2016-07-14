<%-- 
    Document   : show
    Created on : Mar 22, 2015, 9:09:01 PM
    Author     : Suzy
--%>

<%@page import="sample.account.AccountDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="sample.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.loginBean.username}</font>
        <h1>Information Page</h1>
        <form action="view.jsp">
            Lastname <input type="text" name="search" value="" /><br/>
            <input type="submit" value="Search" /><br/><br/>
        </form>
        <jsp:useBean id="searchBean" class="sample.bean.SearchBean" scope="session"/>
        <jsp:setProperty name="searchBean" property="search" value="<%= search %>"/>
        <%
            searchBean.setSearch(request.getParameter("search"));
            searchBean.searchLikeLastname();
            List<AccountDTO> list = searchBean.getList();
            if (list != null) {
        %>
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
                    for (int i = 0; i < list.size(); i++) {
                        AccountDTO dto = list.get(i);
                %>
                <tr>
                    <td><%= (i + 1)%></td>
                    <td><%= dto.getUsername()%></td>
                    <td>
                        <a href="viewDetails.jsp?username=<%= dto.getUsername()%>">ViewDetail</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        No record matched!!!
        <%
            }
        %>
    </body>
</html>
