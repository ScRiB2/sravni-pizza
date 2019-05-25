<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<html>

<head>
    <title>List Customers</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>List of Pizza</h2>
    </div>
</div>
<div id="container">
    <div id=content>

        <input type="button" value="Add Pizza"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />

        <table>
            <tr>
                <th>Name</th>
                <th>Image</th>
                <th>Price</th>
                <th>Size</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempPizza" items="${pizzas}">
                <!-- construct an update-->
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempPizza.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/pizza/delete">
                    <c:param name="customerId" value="${tempPizza.id}"/>
                </c:url>
                <tr>
                    <td>${tempPizza.name}</td>
                    <td><img src="${tempPizza.image}"/></td>
                    <td>${tempPizza.price}</td>
                    <td>${tempPizza.size.name}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


</body>
</html>
