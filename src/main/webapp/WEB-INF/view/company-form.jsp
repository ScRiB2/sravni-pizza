<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>

<html>
<head>
    <title>Сохранить компанию</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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

    <form:form action="save" modelAttribute="company" method="post">
        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>Название:</label></td>
                <td><form:input path="name"/></td>
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
        <a href="${pageContext.request.contextPath}/pizza/list">Вернуться в список пицц</a>
    </p>

</div>

</body>
</html>
