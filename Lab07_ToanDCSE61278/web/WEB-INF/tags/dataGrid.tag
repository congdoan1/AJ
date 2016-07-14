<%-- 
    Document   : dataGrid
    Created on : Mar 25, 2015, 7:23:57 PM
    Author     : Suzy
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="url" required="true"%>
<%@attribute name="user" required="true"%>
<%@attribute name="pass" required="true"%>
<%@attribute name="sql" required="true"%>
<%@tag dynamic-attributes="parList" %>

<%-- any content can be specified here e.g.: --%>
<c:catch var="ex">
    <sql:setDataSource var="con" url="${url}" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" user="${user}" password="${pass}"/>
    <c:if test="${not empty con}">
        <sql:query var="rs" dataSource="${con}">
            ${sql}
            <c:forEach var="par" items="${parList}">
                <sql:param value="${param}"></sql:param>
            </c:forEach>
        </sql:query>
        <c:if test="${not empty rs}">
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
                            <td><a href="">ViewDetail</a> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </c:if>
</c:catch>
<c:if test="${not empty ex}">
    <font color="red">Error: ${ex}</font>
</c:if>