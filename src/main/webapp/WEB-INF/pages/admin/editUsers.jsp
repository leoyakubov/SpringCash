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
                var url = "/admin/deleteUser.htm?userId=" + id;
                var OK = confirm('Delete this user?');
                if (OK) {
                    console.log("URL: " + url);
                    window.location = url;
                }
            }

            function setUpdated(id) {
                //id--;
                var checkboxId = "userListItems" + id + ".updated1";
                console.log("ID: " + checkboxId);
                document.getElementById(checkboxId).checked = true;
            }
        </script>
    </head>
    <body>
        <h2><spring:message code="label.users"/></h2>

        <c:if test="${not empty successMsg}">
            <label style="color: green">${successMsg}</label>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <label style="color: red">${errorMsg}</label>
        </c:if>

        <form:form method="get" action="/admin/addUser.htm" commandName="user">
            <input type="submit" value="<spring:message code="label.adduser"/>"/>
        </form:form>

        <c:if  test="${!empty userList}">
            <form:form method="post" action="saveUsers.htm" modelAttribute="userListModel">
                <table class="data">
                    <tr>
                        <th>&nbsp;</th>
                        <th>No.</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Is Active</th>
                        <th>Role</th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:forEach items="${userListModel.userListItems}" var="user" varStatus="status">
                        <tr>
                            <td><form:checkbox path="userListItems[${status.index}].updated"/></td>
                            <td align="center">${status.count}</td>
                            <td><form:label path="userListItems[${status.index}].firstName">${user.firstName}</form:label></td>
                            <td><form:label path="userListItems[${status.index}].lastName">${user.lastName}</form:label></td>
                            <td><form:label path="userListItems[${status.index}].login">${user.login}</form:label></td>
                            <td><form:label path="userListItems[${status.index}].email">${user.email}</form:label></td>
                            <td><form:checkbox path="userListItems[${status.index}].active" onclick='javascript:setUpdated("${status.index}")'/></td>
                            <td><form:select path="userListItems[${status.index}].role.name" onchange='javascript:setUpdated("${status.index}")'>
                                    <form:options items="${roleList}" itemValue="name" itemLabel="name"/>
                                </form:select>
                            </td>
                            <td><a href="#" onclick='javascript:deleteItem("${user.id}")'><spring:message code="label.deleteuser"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <div>
                    <input type="submit" value="Save"/>
                    <input type="reset" value="<spring:message code="label.reset"/>"/>
                </div>
            </form:form>
        </c:if>
    </body>
</html>
