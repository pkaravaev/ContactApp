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
    <title>Contact list - Contact Application</title>
    <s:url var="url_css" value="../../static/css/style.css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css">
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
            <h3>Contact list</h3>
            <table border="1">
                <tr>
                    <td>SR</td>
                    <td>CID</td>
                    <td>NAME</td>
                    <td>PHONE</td>
                    <td>EMAIL</td>
                    <td>ADDRESS</td>
                    <td>REMARK</td>
                    <td>ACTION</td>
                </tr>
                <c:forEach var="contact" items="${contactList}" varStatus="st">
                   <tr>
                       <td>${st.count}</td>
                       <td>${contact.contactid}</td>
                       <td>${contact.name}</td>
                       <td>${contact.phone}</td>
                       <td>${contact.email}</td>
                       <td>${contact.address}</td>
                       <td>${contact.remark}</td>
                       <td>EDIT | DELETE</td>
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
