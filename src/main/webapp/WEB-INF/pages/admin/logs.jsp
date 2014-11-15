<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 14.11.14
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title></title>
        <link rel="stylesheet" type="text/css" media="all" href="<c:url value="/static/css/main.css"/>"/>
    </head>
    <body>
        <h2>System logs</h2>
            <c:if  test="${!empty logList}">
                <table id="logstTable">
                    <tr>
                        <th>no.</th>
                        <th>timestamp</th>
                        <th>user</th>
                        <th>action</th>
                        <th>status</th>
                        <th>description</th>
                        <th>area</th>
                    </tr>

                    <c:forEach items="${logList}" var="logRecord" varStatus="status">
                        <tr>
                            <td align="center">${status.count}</td>
                            <td><label>${logRecord.timestamp}</label></td>
                            <td><label>${logRecord.user.login}</label></td>
                            <td><label>${logRecord.action}</label></td>
                            <td><label>${logRecord.status}</label></td>
                            <td><label>${logRecord.description}</label></td>
                            <td><label>${logRecord.area}</label></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
    </body>
</html>
