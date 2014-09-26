<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 26.09.2014
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head></head>
<body>
<h1>Login</h1>
<form name='f' action="j_spring_security_check" method='POST'>
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
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
        <tr>
            <td>Remember Me:</td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>
    </table>
</form>
</body>
</html>
