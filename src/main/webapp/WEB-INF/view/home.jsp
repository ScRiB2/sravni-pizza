<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Это домашняя страницы</title>
</head>
<body>
Здесь ничего нет
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Выйти">
</form:form>

<hr>
<p>
    <a href="${pageContext.request.contextPath}/lk">Личный кабинет пользователя</a>
</p>
<p>
    <a href="${pageContext.request.contextPath}/admin">Кабинет администратора</a>
</p>
<hr>
</body>
</html>
