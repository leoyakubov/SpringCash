<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 21.08.2014
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
</head>
<body>
    <h1>Welcome home
    <security:authorize access="isAuthenticated()">
        <span>, <security:authentication property="principal.username"/></span>
    </security:authorize>
    </h1>

    <c:if test="${not empty successMsg}">
        <label style="color: green">${successMsg}</label>
    </c:if>
</body>
</html>
