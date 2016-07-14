<%-- 
    Document   : viewDetails
    Created on : Mar 22, 2015, 9:45:09 PM
    Author     : Suzy
--%>

<%@page import="sample.bean.LoginBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="sample.account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Detail</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.loginBean.username}</font>
        <h1>Information Details</h1>
        <jsp:useBean id="searchBean" class="sample.bean.SearchBean" scope="session" />
        <jsp:useBean id="loginBean" class="sample.bean.LoginBean" scope="session" />
        <%
            String username = request.getParameter("username");
            String currUser = (String) loginBean.getUsername();
            String searchValue = searchBean.getSearch();
            if (username.equals(currUser) || loginBean.isRoles()) {
                searchBean.setSearch(username);
                AccountDTO dto = searchBean.searchByUsername();
        %>
        Username: <%= dto.getUsername() %><br/>
        Lastname: <%= dto.getLastname()%><br/>
        Roles: <%
            if (dto.isRoles()) {
        %><%= "Admin"%><%
                } else {
        %><%= "User"%><%
                        }
                    } else {
                %>
        No permission to access!!!<br/>    
        <%
            }
        %>
        <h3>Click <a href="view.jsp?search=<%= searchValue %>">here</a> to return search page!!!</h3>
    </body>
</html>
