<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 14.08.14
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

            function setUpdatingUserCheckbox(id) {
                document.getElementById("")
            }
        </script>
    </head>
    <body>
        <h2><spring:message code="label.users"/></h2>

        <c:if test="${not empty successMsg}">
            <label style="color: green">${successMsg}</label>
        </c:if>

        <form:form method="get" action="/user/add.htm" commandName="user">
            <input type="submit" value="<spring:message code="label.adduser"/>"/>
        </form:form>

        <c:if  test="${!empty userList}">
            <form:form method="post" action="saveUsers.htm" modelAttribute="userListCommand">
                <table class="data">
                    <tr>
                        <th>&nbsp;</th>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Is Active</th>
                        <th>Role</th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:forEach items="${userListCommand.users}" var="user" varStatus="status">
                        <tr>
                            <td><%--<form:checkbox path="updatedUsers[${status.index}].value"/>--%></td>
                            <td align="center">${status.count}</td>
                            <td>${user.firstName}, ${user.lastName}</td>
                            <td>${user.login}</td>
                            <td>${user.email}</td>
                            <td><form:checkbox path="users[${status.index}].active" onclick='javascript:setUpdatingUserCheckbox("${user.id}")'/></td>
                            <td><form:select path="users[${status.index}].role.name">
                                    <form:options items="${roleList}" itemValue="name" itemLabel="name"/>
                                </form:select></td>


                            <%-- <td><a href="/user/edit.htm?userId=${user.id}"><spring:message code="label.edituser"/></a></td>
                            <td><form:errors path="user" cssStyle="color: #ff0000;"/></td>--%>
                            <td><a href="#" onclick='javascript:deleteItem("${user.id}")'><spring:message code="label.deleteuser"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Save" />
            </form:form>
        </c:if>
    </body>
</html>
