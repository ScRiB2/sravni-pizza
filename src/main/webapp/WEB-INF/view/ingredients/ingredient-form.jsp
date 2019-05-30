<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>

<html>
<head>
    <title>Сохранить ингредиент</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
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
        <h2>Список ингредиентов</h2>
    </div>
</div>

<div id="container">
    <h3>Сохранить ингредиент</h3>

    <form:form action="save" modelAttribute="ingredient" method="post">
        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>Название:</label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><label>Категория:</label></td>
                <td><form:select path="categoryIngredient">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                </form:select>
                    <input type="button" value="Добавить"
                           onclick="window.location.href='/categories/add'; return false;"
                           class="add-button"
                    />
                </td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Сохранить" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div style="clear; both;"></div>
    <p>
        <a href="${pageContext.request.contextPath}/ingredients/list">Вернуться в список</a>
    </p>

</div>
</body>
</html>
