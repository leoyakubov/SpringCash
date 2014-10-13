<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 14.08.14
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <script type="text/javascript">
        function deleteItem(id) {
            var url = "/user/delete.htm?userId=" + id;
            var OK = confirm('Delete this user?');
            if (OK) {
                window.location = url;
            }
        }
    </script>
</head>
<body>
    <h2><spring:message code="label.users"/></h2>
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

            <div>${errorMessage}</div>
            <div>${successMessage}</div>

            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.firstName}, ${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.active}</td>
                    <td>${user.role.name}</td>
                    <td><a href="/user/edit.htm?userId=${user.id}"><spring:message code="label.edituser"/></a></td>
                    <td><form:errors path="user" cssStyle="color: #ff0000;"/></td>
                    <td><a href="#" onclick='javascript:deleteItem("${user.id}")'><spring:message code="label.deleteuser"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <form:form method="get" action="add.htm" commandName="user">
        <input type="submit" value="<spring:message code="label.adduser"/>"/>
    </form:form>
</body>
</html>
