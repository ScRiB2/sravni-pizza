<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход на сайт</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="https://www.rudebox.org.ua/favicon.ico"/>
    <link rel='stylesheet prefetch' href='https://www.rudebox.org.ua/demo/lessons/styles/style.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>

<body class="login-page">
<main>
    <div class="login-block">
        <img src="${pageContext.request.contextPath}/resources/image/1.png" alt="Scanfcode">
        <h1>Введите свои данные</h1>
        <form:form action="${pageContext.request.contextPath}/authenticateTheUser"
                   method="post">
            <c:if test="${param.error != null}">
                <i class="failed">Ошибка! Повторите вход</i>
            </c:if>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <input type="text" class="form-control" name="username" placeholder="Ваш логин">
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <input type="password" class="form-control" name="password" placeholder="Ваш пароль">
                </div>
            </div>

            <button class="btn btn-primary btn-block" type="submit">ВОЙТИ НА САЙТ</button>

        </form:form>
    </div>

    <div class="login-links">
        <p class="text-center">Еще нету аккаунта? <a class="txt-brand"
                                                     href="${pageContext.request.contextPath}/register"><font
                color=#29aafe>Регистрируйся</font></a>
        </p>
    </div>
</main>
</body>

</html>
