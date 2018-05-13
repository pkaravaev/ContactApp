<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contact_form Contact Application</title>
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
            <h3>Contact Form</h3>

            <c:if test="${err!=null}">
                <p class="error">${err}</p>
            </c:if>

            <s:url var="url_csave" value="/user/save_contact"/>
            <f:form action="${url_csave}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td><f:input path="name"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><f:input path="phone"/></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><f:input path="email"/></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><f:textarea path="address"/></td>
                    </tr>
                    <tr>
                        <td>Remark</td>
                        <td><f:textarea path="remark"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <button>Save</button>
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
