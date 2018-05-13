<%--
  Created by IntelliJ IDEA.
  User: karav
  Date: 28.04.2018
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User list- Contact Application</title>
    <s:url var="url_css" value="../../static/css/style.css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css">
    <s:url value="/static/js/jquery-3.3.1.min.js" var="url_jqlib"/>
    <script src="${url_jqlib}"></script>
    <script>
        function changeStatus(uid, lstatus) {
            alert(uid + ", " + lstatus);

            $.ajax({
                url: 'change_status',
                data: {userId: uid, loginStatus: lstatus},
                success: function (data) {
                    alert(data);
                }
            });
        }
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
            <h3>User list</h3>
            <table border="1">
                <tr>
                    <th>USER ID</th>
                    <th>NAME</th>
                    <th>PHONE</th>
                    <th>EMAIL</th>
                    <th>ADDRESS</th>
                    <th>LOGIN NAME</th>
                    <th>USERNAME</th>
                    <th>STATUS</th>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="st">
                    <tr>
                        <td>${st.count}</td>
                        <td>${user.userid}</td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.address}</td>
                        <td>${user.loginname}</td>
                        <td>
                            <select id="id_${user.userid}" onchange="changeStatus(${user.userid}, $(this).val())">
                                <option value="1">Active</option>
                                <option value="2">Block</option>
                            </select>
                            <script>
                                $('#id_${user.userid}').val('${user.loginStatus}')
                            </script>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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
