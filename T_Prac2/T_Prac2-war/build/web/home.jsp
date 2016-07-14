<%-- 
    Document   : home
    Created on : Apr 2, 2015, 10:16:36 PM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <h1>Search Movie by year</h1>
        <form action="CenterServlet">
            From <input type="text" name="txtFrom" value="${param.txtFrom}" /><br/>
            To <input type="text" name="txtTo" value="${param.txtTo}" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Year</th>
                        <th>Duration</th>
                        <th>Genre</th>
                        <th>Studio</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="mv" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${mv.TMoviesPK.title}
                                <input type="hidden" name="txtTitle" value="${mv.TMoviesPK.title}" />
                            </td>
                            <td>
                                ${mv.TMoviesPK.year}
                                <input type="hidden" name="txtYear" value="${mv.TMoviesPK.year}" />
                            </td>
                            <td>
                                <input type="text" name="txtDuration" value="${mv.duration}" />
                            </td>
                            <td>
                                <input type="text" name="txtGenre" value="${mv.genre}" />
                            </td>
                            <td>
                                <input type="text" name="txtStudio" value="${mv.studio}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtTitle" value="${mv.TMoviesPK.title}"/>
                                    <c:param name="txtYear" value="${mv.TMoviesPK.year}"/>
                                    <c:param name="lastSearchValueFrom" value="${param.txtFrom}"/>
                                    <c:param name="lastSearchValueTo" value="${param.txtTo}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
                                <input type="hidden" name="lastSearchValueFrom" value="${param.txtFrom}" />
                                <input type="hidden" name="lastSearchValueTo" value="${param.txtTo}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
