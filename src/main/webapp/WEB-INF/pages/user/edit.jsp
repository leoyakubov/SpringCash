<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 20.09.14
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
    <head>
        <title></title>
    </head>
    <body>
        <h2>Edit user</h2>
        <c:if test="${not empty successMsg}">
            <label style="color: green">${successMsg}</label>
        </c:if>
        <c:if  test="${!empty userModel}">
            <form:form method="post" action="edit.htm" commandName="userModel">
                <table>
                    <tr>
                        <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
                        <td><form:input path="login" readonly="true"/></td>
                        <td><form:errors path="login" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
                        <td><form:input path="email" /></td>
                        <td><form:errors path="email" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName"><spring:message code="label.firstname"/></form:label></td>
                        <td><form:input path="firstName" /></td>
                        <td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName"><spring:message code="label.lastname"/></form:label></td>
                        <td><form:input path="lastName" /></td>
                        <td><form:errors path="lastName" cssStyle="color: #ff0000;"/></td>
                    </tr>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <tr>
                            <td><form:label path="active"><spring:message code="label.isactive"/></form:label></td>
                            <td><form:checkbox path="active" /></td>
                        </tr>
                        <tr>
                            <td><form:label path="role"><spring:message code="label.role"/></form:label></td>
                            <td><form:select path="role.name">
                                <form:options items="${roleList}" itemValue="name" itemLabel="name"/>
                            </form:select>
                            </td>
                        </tr>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <td><form:input path="role.name" type="hidden" value="${user.role.name}"/></td>
                    </sec:authorize>
                    <tr>
                        <td><form:input path="id" type="hidden" value="${user.id}"/></td>
                    </tr>
                    <br/>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="<spring:message code="label.save"/>"/>
                        </td>
                        <td colspan="2">
                            <input type="reset" value="<spring:message code="label.reset"/>" <%--onclick="location.href='list.htm'"--%>/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </c:if>
    </body>
</html>
