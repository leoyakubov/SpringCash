<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 18.08.2014
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAnonymous()">
    <%
        response.sendRedirect("home.htm");
    %>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <%
        response.sendRedirect("admin.htm");
    %>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">
    <%
        response.sendRedirect("home.htm");
    %>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_DEMO')">
    <%
        response.sendRedirect("demo.htm");
    %>
</sec:authorize>