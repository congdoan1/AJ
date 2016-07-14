<%-- 
    Document   : search
    Created on : Mar 23, 2015, 4:54:27 PM
    Author     : Suzy
--%>

<%@page import="java.util.Date"%>
<%@page import="sample.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">Welcome, <%= session.getAttribute("USER")%></font><br/>
        <h1>Search page</h1>
        <%= new Date()%>
        <% int sum = 0;
            for (int j = 0; j < 10; j++) { %>
        <% sum = sum  + j; %>
        <% } %>

        <form action="CenterServlet">
            Name <input type="text" name="txtName" value="" />
            <input type="submit" value="Search" name="btAction" />     
        </form><br/>
        <%
            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("LIST");
            if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Roles</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < result.size(); i++) {
                        RegistrationDTO dto = result.get(i);
                        String urlRewritting = "CenterServlet?btAction=Delete&txtUsername="
                                + dto.getUsername()
                                + "&lastSearchValue=" + request.getParameter("txtName");
                %>
            <form action="CenterServlet">
                <tr>
                    <td><%= (i + 1)%></td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ADMIN" 
                               <%
                                   if (dto.isRoles()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td><a href="<%= urlRewritting%>">Delete</a></td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="
                               <%= request.getParameter("txtName")%>
                               " />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>
</body>
</html>
