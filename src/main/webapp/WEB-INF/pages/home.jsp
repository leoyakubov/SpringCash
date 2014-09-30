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

<html>
<head>
</head>
<body>
    <h1>${welcomeMsg} Home</h1>
    <c:if test="${not empty msg}">
        <label style="color: green">${msg}</label>
    </c:if>
</body>
</html>
