<%-- 
    Document   : viewDetails
    Created on : Mar 22, 2015, 9:45:09 PM
    Author     : Suzy
--%>

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
        <font color="red">Welcome <%= session.getAttribute("USER")%></font>
        <h1>Information Details</h1>
        <%
            String roles = (String) session.getAttribute("ROLE");
            String username = request.getParameter("txtUsername");
            String searchValue = request.getParameter("lastSearchValue");
            String urlRewritting = "CenterServlet?"
                    + "btAction=Search&txtSearch="
                    + searchValue;
            if (roles.equals("Admin") || username.equals((String) session.getAttribute("USER"))) {
                HashMap<String, AccountDTO> result = (HashMap<String, AccountDTO>) session.getAttribute("RESULT");
                if (result != null) {
                    AccountDTO dto = result.get(username);
        %>
        Username: <%= dto.getUsername()%><br/>
        Lastname: <%= dto.getLastname()%><br/>
        Roles: 
        <%
            if (dto.isRoles()) {
        %>
        <%= "Admin"%>
        <%
        } else {
        %>
        <%= "User"%>
        <%
                }
            }
        } else {
        %>
        No permission to access<br/>
        <%
            }
        %>
        <h3>Click <a href="<%= urlRewritting%>">here</a> to return search page!!!</h3>
    </body>
</html>
