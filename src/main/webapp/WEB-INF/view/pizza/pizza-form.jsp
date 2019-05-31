<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>

<html>
<head>
    <title>Сохранить пиццу</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Список пицц</h2>
    </div>
</div>

<div id="container">
    <h3>Сохранить пиццу</h3>

    <form:form action="savePizza" modelAttribute="pizza" method="post">
        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>Название:</label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><label>Изображение:</label></td>
                <td><form:input path="image"/></td>
            </tr>
            <tr>
                <td><label>Цена:</label></td>
                <td><form:input path="price"/></td>
            </tr>
            <tr>
                <td><label>Размер:</label></td>
                <td><form:select path="size" items="${sizes}"/></td>
            </tr>
            <tr>
                <td><label>Компания:</label></td>
                <td><form:select path="company">
                    <form:options items="${companies}" itemValue="id" itemLabel="name"/>
                </form:select>
                    <input type="button" value="Добавить"
                           onclick="window.location.href='/company/add'; return false;"
                           class="add-button"
                    />
                </td>
            </tr>
            <tr>
                <td><label>Ингредиенты:</label></td>
                <td><c:forEach var="ingredient" items="${pizza.ingredients}">
                    <form:hidden path="ingredients"/>
                    <p>${ingredient.name}</p>
                </c:forEach>
                    <input type="submit" name="add-ingredient" value="Изменить"
                           class="add-button"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" name="save" value="Сохранить" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div style="clear; both;"></div>
    <p>
        <a href="${pageContext.request.contextPath}/pizza/list">Вернуться в список</a>
    </p>

</div>
</body>
</html>
