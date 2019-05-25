<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ScRiB
  Date: 30.04.2019
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Customer</title>

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
        <h2>List of Pizza</h2>
    </div>
</div>

<div id="container">
    <h3>Save Pizza</h3>

    <form:form action="savePizza" modelAttribute="pizza" method="post">
        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>
                <td><label>Name:</label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><label>Image:</label></td>
                <td><form:input path="image"/></td>
            </tr>
            <tr>
                <td><label>Price:</label></td>
                <td><form:input path="price"/></td>
            </tr>
            <tr>
                <td><label>Size:</label></td>
                <td><form:select path="size" items="${sizes}" /></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div style="clear; both;"></div>
    <p>
        <a href="${pageContext.request.contextPath}/pizza/list">Back to List</a>
    </p>

</div>

</body>
</html>
