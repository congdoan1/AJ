<%-- 
    Document   : search
    Created on : Mar 23, 2015, 4:54:27 PM
    Author     : Suzy
--%>

<%@page import="sample.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font><br/>
        <h1>Search page</h1>
        <form action="CenterServlet">
            Name <input type="text" name="txtName" value="${param.txtName}" />
            <input type="submit" value="Search" name="btAction" />     
        </form><br/>


        <c:set var="info" value="${requestScope.LIST}"/>
        <c:if test="${not empty info}">
            Search Result<br/>
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
                    <c:forEach var="dto" items="${info}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>${dto.lastname}</td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ADMIN" 
                                       <c:if test="${dto.roles}">checked="checked"</c:if>
                                           />
                                </td>
                            <c:url var="deleteLink" value="CenterServlet">
                                <c:param name="btAction" value="Delete" />
                                <c:param name="txtUsername" value="${dto.username}" />
                                <c:param name="lastSearchValue" value="${param.txtName}" />
                            </c:url>
                            <td><a href="${deleteLink}">Delete</a> </td>
                            <td>
                                <input type="hidden" name="lastSearchValue" value="${param.txtName}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

    </c:if>

    <%--
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
%>--%>
</body>
</html>
