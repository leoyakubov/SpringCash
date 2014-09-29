<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 26.09.2014
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
    <head></head>
    <body onload='document.loginForm.username.focus();'>
        <div id="login-box">
            <h3>Login with Username and Password</h3>

            <c:if test="${not empty error}">
                <div style="text-decoration-color: red" class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form name='loginForm'
                  action="<c:url value='/j_spring_security_check'/>" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='j_username' value=''></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='j_password' /></td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit" value="submit" />
                        </td>
                    </tr>
                    <tr>
                        <td>Remember Me:</td>
                        <td><input type="checkbox" name="_spring_security_remember_me" /></td>
                    </tr>
                </table>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </div>
    </body>
</html>
