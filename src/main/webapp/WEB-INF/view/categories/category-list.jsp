<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Список категорий</title>
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
        <h2>Список категорий</h2>
    </div>
</div>
<div id="container">
    <div id=content>

        <input type="button" value="Добавить категорию"
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
        <input type="button" value="Список ингредиентов"
               onclick="window.location.href='/ingredients/list'; return false;"
               class="add-button"
        />
        <input style="margin-left: 30%" type="button" value="На главную"
               onclick="window.location.href='/catalog/list'; return false;"
               class="add-button text-right"
        />
        <table>
            <tr>
                <th>Название</th>
                <th>Количество ингридиентов</th>
                <th>Действия</th>
            </tr>

            <c:forEach var="category" items="${categories}">
                <!-- construct an update-->
                <c:url var="updateLink" value="/categories/update">
                    <c:param name="categoryId" value="${category.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/categories/delete">
                    <c:param name="categoryId" value="${category.id}"/>
                </c:url>
                <c:url var="infoLink" value="/categories/info">
                    <c:param name="categoryId" value="${category.id}"/>
                </c:url>
                <tr>
                    <td>${category.name}</td>
                    <td>${category.ingredients.size()}</td>
                    <td>
                        <a href="${updateLink}">Обновить</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Вы точно хотите удалить эту категорию?'))) return false">Удалить</a>
                        |
                        <a href="${infoLink}">Подробнее</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
