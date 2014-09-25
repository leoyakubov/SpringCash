<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 18.08.2014
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="menuPane">
    <ul class="mainMenu">
        <li><a href="<c:url value="/home.htm"/>"><spring:message code="title.home"/></a></li>
        <li><a href="<c:url value="/user/list.htm"/>"><spring:message code="title.users"/></a></li>
        <li><a href="<c:url value="/about.htm"/>"><spring:message code="title.about"/></a></li>
    </ul>
</div>
