<%-- 
    Document   : show
    Created on : Mar 22, 2015, 9:09:01 PM
    Author     : Suzy
--%>

<%@page import="sample.account.AccountDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
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
        <font color="red">Welcome <%= session.getAttribute("USER") %></font>
        <h1>Information Page</h1>
        <form action="CenterServlet">
            Lastname <input type="text" name="txtSearch" value="" /><br/>
            <input type="submit" value="Search" name="btAction" /><br/><br/>
            <%
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
            %>
        </form>
    </body>
</html>
