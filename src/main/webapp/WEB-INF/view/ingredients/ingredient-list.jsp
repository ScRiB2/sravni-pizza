<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Список ингредиентов</title>
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
        <h2>Список ингредиентов</h2>
    </div>
</div>
<div id="container">
    <div id=content>

        <input type="button" value="Добавить ингредиент"
               onclick="window.location.href='add'; return false;"
               class="add-button"
        />
        <input type="button" value="Список пицц"
               onclick="window.location.href='/pizza/list'; return false;"
               class="add-button"
        />
        <input type="button" value="Список компаний"
               onclick="window.location.href='/company/list'; return false;"
               class="add-button"
        />
        <input type="button" value="Список категорий"
               onclick="window.location.href='/categories/list'; return false;"
               class="add-button"
        />
        <input style="margin-left: 30%" type="button" value="На главную"
               onclick="window.location.href='/'; return false;"
               class="add-button text-right"
        />
        <table>
            <tr>
                <th>Название</th>
                <th>Категория</th>
                <th>Действия</th>
            </tr>

            <c:forEach var="ingredient" items="${ingredients}">
                <!-- construct an update-->
                <c:url var="updateLink" value="/ingredients/update">
                    <c:param name="ingredientId" value="${ingredient.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/ingredients/delete">
                    <c:param name="ingredientId" value="${ingredient.id}"/>
                </c:url>
                <tr>
                    <td>${ingredient.name}</td>
                    <td>${ingredient.categoryIngredient.name}</td>
                    <td>
                    <a href="${updateLink}">Обновить</a>
                    |
                    <a href="${deleteLink}"
                       onclick="if (!(confirm('Вы точно хотите удалить этот ингредиент?'))) return false">Удалить</a>
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
