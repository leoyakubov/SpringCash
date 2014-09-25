<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 18.08.2014
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Set page title as "SpringCash - <page title>" -->
    <c:set var="titleKey">
        <tiles:insertAttribute name="pageTitle" ignore="true"/>
    </c:set>
    <title><spring:message code="title.apptitle"/> - <spring:message code="${titleKey}"/></title>

    <link rel="stylesheet" type="text/css" media="all" href="<c:url value="/static/css/tiles.css"/>"/>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="main">
    <tiles:insertAttribute name="menu"/>
    <div class="bodyPane">
        <tiles:insertAttribute name="body"/>
    </div>
</div>
<tiles:insertAttribute name="footer"/>

</body>
</html>