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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Contact list - Contact Application</title>
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
            <h3>Contact list</h3>

            <c:if test="${param.act eq 'sv'}">
            <p class="success">Contact Saved Successfully!!</p>
            </c:if>

            <c:if test="${param.act eq 'del'}">
            <p class="success">Contact Deleted Successfully!!</p>
            </c:if>

            <table align="right">
                <tr>
                    <td>
                        <form action="<s:url value="/user/contact_search"/>">
                            <input type="text" name="freeText" value="${param.freeText}"
                                   placeholder="Enter Text to Search">
                            <button>Find</button>
                        </form>
                    </td>
                </tr>
            </table>

            <form action="<s:url value="/user/bulk_cdelete"/>">
                <button>Delete Selected Records</button>
                <table border="1" cellpadding="5">
                    <tr>
                        <th>SELECT</th>
                        <th>CID</th>
                        <th>NAME</th>
                        <th>PHONE</th>
                        <th>EMAIL</th>
                        <th>ADDRESS</th>
                        <th>REMARK</th>
                        <th>ACTION</th>
                    </tr>

                    <c:if test="${empty contactList}">
                    <tr>
                        <td align="center" colspan="8" class="error">No records Present</td>
                    </tr>
                    </c:if>
                    <c:forEach var="contact" items="${contactList}" varStatus="st">
                    <tr>
                        <td align="center"><input type="checkbox" name="cid" value="${contact.contactid}"></td>
                        <td>${contact.contactid}</td>
                        <td>${contact.name}</td>
                        <td>${contact.phone}</td>
                        <td>${contact.email}</td>
                        <td>${contact.address}</td>
                        <td>${contact.remark}</td>

                        <s:url var="url_del" value="/user/del_contact">
                            <s:param name="cid" value="${contact.contactid}"/>
                        </s:url>

                        <s:url var="url_edit" value="/user/edit_contact">
                            <s:param name="cid" value="${contact.contactid}"/>
                        </s:url>

                        <td><a href="${url_edit}">Edit</a> | <a href="${url_del}">Delete</a></td>
                    </tr>
                    </c:forEach>
            </form>
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
