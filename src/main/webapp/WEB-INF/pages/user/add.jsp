<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 20.09.14
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title></title>
</head>
<body>
    <h2>Add new user</h2>
    <form:form method="post" action="add.htm" commandName="user">
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
            <br/>
            <tr>
                <td colspan="2">
                    <input type="submit" value="<spring:message code="label.adduser"/>"/>
                </td>
                <td colspan="2">
                    <input type="button" value="<spring:message code="label.cancel"/>" onclick="location.href='list.htm'"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
