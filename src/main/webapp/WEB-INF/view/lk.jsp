<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>
<head>
    <title>Удачный вход</title>
</head>
<body>
Поздравляю. Вы вошли на сайт!

<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Выйти">
</form:form>
</body>
</html>
