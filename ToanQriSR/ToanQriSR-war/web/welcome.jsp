<%-- 
    Document   : welcome
    Created on : Apr 1, 2015, 9:55:41 AM
    Author     : Suzy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USER}</font>
        <h1>Hello World!</h1>
        <form action="CenterServlet">
            <input type="submit" value="List" name="btAction" />
            <a href="insert.jsp">Insert Book</a>
        </form><br/>
        <c:set var="list" value="${requestScope.LIST}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${list}" varStatus="counter">
                    <form action="CenterServlet">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${book.isbn}
                                <input type="hidden" name="txtISBN" value="${book.isbn}" />
                            </td>
                            <td>
                                <input type="text" name="txtTitle" value="${book.title}" />
                            </td>
                            <td>
                                <input type="text" name="txtPrice" value="${book.price}" />
                            </td>
                            <td>
                                <c:url var="delete" value="CenterServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="txtISBN" value="${book.isbn}"/>
                                </c:url>
                                <a href="${delete}">Delete</a>
                            </td>
                            <td>
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
