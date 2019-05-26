<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>

<head>
    <title>Список пицц</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <style>
        .img{
            width:100px;
            height:100px;
        }

        .image-pizza{
            max-width:100%;
            max-height: 100%;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Список пицц</h2>
    </div>
</div>
<div id="container">
    <div id=content>

        <input type="button" value="Добавить пиццу"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />

        <table>
            <tr>
                <th>Название</th>
                <th>Изображение</th>
                <th>Цена</th>
                <th>Размер</th>
                <th>Действия</th>
            </tr>

            <c:forEach var="tempPizza" items="${pizzas}">
                <!-- construct an update-->
                <c:url var="updateLink" value="/p/showFormForUpdate">
                    <c:param name="customerId" value="${tempPizza.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/pizza/delete">
                    <c:param name="customerId" value="${tempPizza.id}"/>
                </c:url>
                <tr>
                    <td>${tempPizza.name}</td>
                    <td class="img"><img src="${tempPizza.image}" class="image-pizza"/></td>
                    <td>${tempPizza.price}</td>
                    <td>${tempPizza.size.name}</td>
                    <td>
                        <a href="${updateLink}">Обновить</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Вы точно хотите удалить эту пиццу?'))) return false">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


</body>
</html>
