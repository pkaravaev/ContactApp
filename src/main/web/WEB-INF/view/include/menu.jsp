<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<s:url var="url_logout" value="/logout"/>
<s:url var="url_register" value="/reg_form"/>
<s:url var="url_login" value=""/>

<c:if test="${sessionScope.userid==null}">
<%--User is not yet logged in : Guest menu--%>
    <a href="#">Home</a> | <a href="${url_login}">Login</a> | <a href="${url_register}">Register</a> | <a href="#">About</a> | <a href="#">Help</a>
</c:if>

<c:if test="${sessionScope.userid!=null && sessionScope.role  == 1 }">
    <%--Admin is logged in : Admin menu--%>
    <a href="#">Home</a> | <a href="<s:url value="/admin/users"/>"> User List</a> | <a href="${url_logout}">Logout</a>
</c:if>

<c:if test="${sessionScope.userid!=null && sessionScope.role  == 2 }">
    <c:url var="url_home" value="/user/dashboard"/>
    <c:url var="url_cform" value="/user/contact_form"/>
    <c:url var="url_clist" value="/user/clist"/>
    <%--General User is logged in : User Menu--%>
    <a href="${url_home}">Home</a> | <a href="${url_cform}">Add Contact</a> | <a href="${url_clist}">Contact List</a> | <a href="${url_logout}"/>Logout</a>
</c:if>
