<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 18.08.2014
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="menuPane">
    <ul class="mainMenu">
        <li><a href="<c:url value="/home.htm"/>">Home</a></li>
        <li><a href="<c:url value="/user/list.htm"/>">Users</a></li>
        <li><a href="<c:url value="/about.htm"/>">About</a></li>
    </ul>
</div>
