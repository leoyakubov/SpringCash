<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 14.08.14
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>SpringCash app</title>
</head>
<body>

<h2>Users</h2>

<form:form method="post" action="addUser" commandName="user">

    <table>
        <tr>
            <td><form:label path="firstName"><spring:message code="label.firstname"/></form:label></td>
            <td><form:input path="firstName" /></td>
        </tr>
        <tr>
            <td><form:label path="lastName"><spring:message code="label.lastname"/></form:label></td>
            <td><form:input path="lastName" /></td>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
            <td><form:input path="login" /></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
            <td><form:password path="password" /></td>
        </tr>
        <tr>
            <td><form:label path="active"><spring:message code="label.isactive"/></form:label></td>
            <td><form:checkbox path="active" /></td>
        </tr>
        <tr>
            <td><form:label path="role"><spring:message code="label.role"/></form:label></td>
            <td><form:select path="role.id">
                    <form:option value="NONE" label="--- Select ---" />
                    <form:options items="${roleList}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="label.adduser"/>"/>
            </td>
        </tr>
    </table>
</form:form>


<h3>Contacts</h3>
<c:if  test="${!empty userList}">
    <table class="data">
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Is Active</th>
            <th>Role</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.lastName}, ${user.firstName} </td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.active}</td>
                <td>${user.role.name}</td>
                <td><a href="deleteUser/${user.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
