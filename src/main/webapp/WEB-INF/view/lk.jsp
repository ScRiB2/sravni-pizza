<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Сравни пиццу</title>
    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,700&subset=latin,cyrillic-ext' rel='stylesheet'
          type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <br/>
    <h1 class="text-center"><big><strong>Сравни пиццу</strong></big></h1>
    <br/>
    <blockquote class="pull-right">
        <h5>Жизнь - это в основном боль и борьба. В остальном - это любовь и пицца...</h5>
        <small><i>Бенедикт Смит</i></small>
    </blockquote>
    <div class="row">
        <security:authorize access="isAuthenticated()">
            <h4>Здравствуйте, <security:authentication property="principal.username"/></h4>
        </security:authorize>
        <ul class="list-inline">
            <security:authorize access="hasRole('USER')">
                <input type="button" value="Главня"
                       onclick="window.location.href='/'; return false;"
                       class="small-good-item__btn-add btn btn-primary btn-sm"
                />
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <input type="button" value="Админка"
                       onclick="window.location.href='/pizza/list'; return false;"
                       class="small-good-item__btn-add btn btn-danger btn-sm"
                />
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <input type="button" value="Войти"
                       onclick="window.location.href='/login'; return false;"
                       class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                />
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <input type="button" value="Выйти"
                       onclick="window.location.href='<c:url value="/logout"/>'; return false;"
                       class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                />
            </security:authorize>

        </ul>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="col-md-12 text-center">
        <h2>Здесь скоро будет огромный функционал</h2>
        <i>Но это не точно</i>
    </div>
</div>
</body>
</html>


</body>
</html>
