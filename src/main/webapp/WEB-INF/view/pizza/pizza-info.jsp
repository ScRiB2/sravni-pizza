<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Информация о пицце</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <style>
        .img {
            width: 100px;
            height: 100px;
        }

        .image-pizza {
            max-width: 100%;
            max-height: 100%;
        }
    </style>
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Информация о пицце "${pizza.name}"</h2>
    </div>
</div>
<div id="container">
    <div id=content>
        <table>
            <tr>
                <td>Название</td>
                <td>${pizza.name}</td>
            </tr>
            <tr>
                <td>Изображение</td>
                <td class="img"><img src="${pizza.image}" class="image-pizza"/></td>
            </tr>
            <tr>
                <td>Цена</td>
                <td>${pizza.price}</td>
            </tr>
            <tr>
                <td>Размер</td>
                <td>${pizza.size.name}</td>
            </tr>
            <tr>
                <td>Компания</td>
                <td>${pizza.company.name}</td>
            </tr>
            <tr>
                <td>Ингредиенты</td>
                <td><p><c:forEach var="ingredient" items="${pizza.ingredients}">
                    ${ingredient.name};
                </c:forEach></p></td>
            </tr>
        </table>
    </div>

    <p>
        <a href="${pageContext.request.contextPath}/pizza/list">Вернуться в список</a>
    </p>
</div>


</body>
</html>
