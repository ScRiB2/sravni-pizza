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
<security:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/login"><font color=#29aafe>Войти</font></a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Выйти">
    </form:form>
</security:authorize>

<hr>
<security:authorize access="hasRole('USER')">
    <p>
        <a href="${pageContext.request.contextPath}/lk">Личный кабинет пользователя</a>
    </p>
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    <p>
        <a href="${pageContext.request.contextPath}/admin">Кабинет администратора</a>
    </p>
</security:authorize>
<hr>
<a href="${pageContext.request.contextPath}/pizza/list">Работа с пиццей</a>
<a href="${pageContext.request.contextPath}/company/list">Список компаний</a>
<a href="${pageContext.request.contextPath}/catalog/list">Каталог с фильтрами</a>
</body>
</html>
