<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css"/>

<head>
    <meta charset="UTF-8">
    <title>Вход в админку</title>
    <link rel="shortcut icon" href="https://www.rudebox.org.ua/favicon.ico"/>
</head>
<body class="admin-login">
<main>
    <div class="container">
        <div class="row">

            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal">
                    <span class="heading">АВТОРИЗАЦИЯ</span>
                    <div class="form-group">
                        <input type="email" class="form-control" id="inputEmail" placeholder="Логин">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Пароль">
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">ВХОД</button>
                    </div>
                </form>
            </div>

        </div><!-- /.row -->
    </div>
</main>
</body>
<!-- /.container -->