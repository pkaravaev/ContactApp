<%--
  Created by IntelliJ IDEA.
  User: karav
  Date: 28.04.2018
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Login- Contact Application</title>
    <s:url var="url_css" value="../../static/style.css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css">
</head>

<s:url var="url_bg" value="/static/bg.jpg"/>

<body background="${url_bg}">

<table border="1" width="80%" align="center">

    <tr>
        <td height="100px">
            <jsp:include page="include/header.jsp"/>
        </td>
    </tr>

    <tr>
        <td height="25px">
            <jsp:include page="include/menu.jsp"/>
        </td>
    </tr>

    <tr>
        <td height="350px" valign="top">
            <h3>User Login</h3>
            <c:if test="${err!=null}">
                <p class="error">${err}</p>
            </c:if>

            <c:if test="${param.act eq 'lo'}">
                <p class="success">Logout Successfully! Thanks for using contact application.</p>
            </c:if>

            <c:if test="${param.act eq 'reg'}">
                <p class="success">User Registered Successfully.</p>
            </c:if>

            <s:url var="url_login" value="/login"/>
            <f:form action="${url_login}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>UserName</td>
                        <td><f:input path="loginName"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><f:password path="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <button>Login</button>
                            <br>
                            <s:url var="url_register" value="/reg_form"/>
                            <a href="${url_register}">New User Registration</a>
                        </td>
                    </tr>
                </table>
            </f:form>
        </td>
    </tr>
    <tr>
        <td height="35px">
            <jsp:include page="include/footer.jsp"/>
        </td>
    </tr>

</table>

</body>
</html>
