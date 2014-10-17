<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 26.09.14
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
    <head>
        <title></title>
    </head>
    <body>
        <h2>Admin page</h2>
        <div>---Links for admin---</div>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>Welcome : ${pageContext.request.userPrincipal.name}
        </c:if>
        <sec:authorize access="isRememberMe()">
            <h2># This user is login by "Remember Me Cookies".</h2>
        </sec:authorize>

        <sec:authorize access="isFullyAuthenticated()">
            <h2># This user is login by username / password.</h2>
        </sec:authorize>
    </body>
</html>
