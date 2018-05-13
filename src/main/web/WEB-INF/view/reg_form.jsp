<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Registration- Contact Application</title>
    <s:url var="url_css" value="../../static/css/style.css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css">
    <s:url value="/static/js/jquery-3.3.1.min.js" var="url_jqlib"/>
    <script src="${url_jqlib}"></script>
    <script>
        $(document).ready(function () {
            $('#id_check_avail').click(function () {
                $.ajax({
                    url: 'check_avail',
                    data: {username: $("#id_username").val()},
                    success: function (data) {
                        $("#id_res_div").html(data);
                    }
                });
            });
        });
    </script>

</head>

<s:url var="url_bg" value="/static/image/bg.jpg"/>

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
            <h3>User Register</h3>

            <c:if test="${err!=null}">
                <p class="error">${err}</p>
            </c:if>

            <s:url var="url_reg" value="/register"/>
            <f:form action="${url_reg}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td><f:input path="user.name"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><f:input path="user.phone"/></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><f:input path="user.email"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><f:textarea path="user.address"/></td>
                    </tr>
                    <tr>
                        <td>UserName</td>
                        <td><f:input id="id_username" path="user.loginname"/>
                            <button type="button" id="id_check_avail">Check Avalability</button>
                            <div id="id_res_div" class="error"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><f:password path="user.password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <button>Submit</button>
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
