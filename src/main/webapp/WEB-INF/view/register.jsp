<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="https://www.rudebox.org.ua/favicon.ico"/>
    <link rel='stylesheet prefetch' href='https://www.rudebox.org.ua/demo/lessons/styles/style.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
    <style>
        .error {
            color: red
        }
    </style>
</head>

<body class="login-page">
<main>
    <div class="login-block">
        <img src="${pageContext.request.contextPath}/resources/image/1.png" alt="Scanfcode">
        <h1>Введите свои данные</h1>
        <form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
                   modelAttribute="crmUser"
                   method="post">

            <!-- Place for messages: error, alert etc ... -->
            <div class="form-group">
                <div class="col-xs-15">
                    <div>
                        <!-- Check for registration error -->
                        <c:if test="${registrationError != null}">
                            <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                    ${registrationError}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <form:errors path="userName" cssClass="error" />
                    <form:input path="userName" class="form-control" placeholder="Ваш логин"/>
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <form:errors path="password" cssClass="error" />
                    <form:password path="password" placeholder="Пароль" class="form-control" />
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                    <form:errors path="matchingPassword" cssClass="error" />
                    <form:password path="matchingPassword" placeholder="Повторите пароль" class="form-control" />
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <form:errors path="firstName" cssClass="error" />
                    <form:input path="firstName" placeholder="Имя" class="form-control" />
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                    <form:errors path="lastName" cssClass="error" />
                    <form:input path="lastName" placeholder="Фамилия" class="form-control" />
                </div>
            </div>

            <hr class="hr-xs">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <form:errors path="email" cssClass="error" />
                    <form:input path="email" placeholder="Почта" class="form-control" />
                </div>
            </div>



            <button class="btn btn-primary btn-block" type="submit">ЗАРЕГИСТРИРОВАТЬСЯ</button>

        </form:form>
    </div>
</main>
</body>
</html>
