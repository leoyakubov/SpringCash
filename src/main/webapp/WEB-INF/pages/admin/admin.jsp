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
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>Welcome : ${pageContext.request.userPrincipal.name}
                <%--| <a href="<c:url value="/j_spring_security_logout"/>" > Logout</a></h2>--%>
        </c:if>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
    </body>
</html>
