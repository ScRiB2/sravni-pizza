<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Добавить ингредиенты</title>
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
        <h2>Добавить ингредиенты к пицце</h2>
    </div>
</div>
<div id="container">
    <div id=content>
        <table>
            <tr>
                <th>Название</th>
                <th>Категория</th>
                <th>Добавить</th>
            </tr>
            <form:form action="addPizza" modelAttribute="pizza" method="post">
                <form:hidden path="id"/>
                <form:hidden path="name"/>
                <form:hidden path="image"/>
                <form:hidden path="price"/>
                <form:hidden path="size"/>
                <form:hidden path="company" tabindex=""/>
                <c:forEach var="ingredient" items="${ingredientsInDb}">
                    <tr>
                        <td>${ingredient.name}</td>
                        <td>${ingredient.categoryIngredient.name}</td>
                        <td>
                            <form:checkbox path="ingredients" value="${ingredient.id}"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td><label></label></td>
                    <td><label></label></td>
                    <td><input type="submit" value="Сохранить" class="save"/></td>
                </tr>
            </form:form>
        </table>
    </div>
</div>


</body>
</html>
