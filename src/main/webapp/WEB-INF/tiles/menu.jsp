<%--
  Created by IntelliJ IDEA.
  User: leonid
  Date: 18.08.2014
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="menuPane">
    <ul class="mainMenu">
       <!-- User section -->
       <div class="user-data">
            <sec:authorize access="isAuthenticated()">
                <div class="user-i fright">
                    <sec:authentication property="principal.username" />
                    <%--(<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>)--%>
                    <a href="javascript:formSubmit()"> Logout</a>

                    <c:url value="/j_spring_security_logout" var="logoutUrl" />
                    <form action="${logoutUrl}" method="post" id="logoutForm">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                    <script>
                        function formSubmit() {
                            document.getElementById("logoutForm").submit();
                        }
                    </script>
                </div>
                <div class="clear"></div>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <div class=" user-i fright">
                    You are not logged in.(<a href="<c:url value="/login.htm"/>">Login</a>)
                </div>
                <div class="clear"></div>
            </sec:authorize>
       </div>
       <br/>


       <!-- Menu section -->
       <li><a href="<c:url value="/home.htm"/>"><spring:message code="title.home"/></a></li>
       <li><a href="<c:url value="/user/list.htm"/>"><spring:message code="title.users"/></a></li>
       <li><a href="<c:url value="/about.htm"/>"><spring:message code="title.about"/></a></li>
       <li><a href="<c:url value="/admin.htm"/>"><spring:message code="title.admin"/></a></li>
       <li><a href="<c:url value="/update.htm"/>">Update</a></li>
    </ul>
</div>
