<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Каталог пицц</title>
    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,700&subset=latin,cyrillic-ext' rel='stylesheet'
          type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body data-page="catalogDB">
<div class="container">
    <br/>
    <br/>
    <br/>
    <br/>
    <div id="filters" class="col-md-12">
        <br/>
        <br/>
        <form:form action="list" modelAttribute="filters" id="filters-form" role="form" method="post">
            <div class="col-md-4">
                <h4>Бренды</h4>
                <div id="brands">
                    <c:forEach var="company" items="${companies}">
                        <div class="checkbox"><label><form:checkbox path="companiesName"
                                                                    value="${company.name}"/> ${company.name}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-md-4">
                <h4>Фильтр по ценам</h4>
                <div id="prices-label">
                Минимальная цена: <form:input path="minPrice" id="min-price" name="min_price" value="${filters.minPrice}"/>
                Максимальная цена: <form:input path="maxPrice" id="max-price" name="max_price" value="${filters.maxPrice}"/>
                </div>
                    <div id="prices"></div>
            </div>
            <div class="col-md-4">
                <h4>Сортировка</h4>
                <br/>
                <form:select path="sort" id="sort" name="sort" class="form-control">
                    <form:option value="0">Cначала дешевые</form:option>
                    <form:option value="1">Cначала дорогие</form:option>
                    <form:option value="2">По названию, А-Я</form:option>
                    <form:option value="3">По названию, Я-А</form:option>
                </form:select>
                <input type="submit" value="Подтвердить"
                       class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                />
            </div>
        </form:form>
    </div>
    <br/>
    <br/>
    <ul id="goods" class="col-md-12">
        <c:forEach var="tempPizza" items="${pizzas}">
            <li class="small-good-item row">
                <div class="col-md-2">
                    <img class="small-good-item__img" src="${tempPizza.image}"/>
                </div>
                <div class="col-md-10">
                    <div class="small-good-item__name">${tempPizza.name} </div>
                    <div class="small-good-item__id">Ингридиенты: ${tempPizza.ingredients.size()} </div>
                    <div class="small-good-item__brand">Фирма: ${tempPizza.company.name}</div>
                    <div class="small-good-item__price">${tempPizza.price} руб.</div>
                    <input type="button" value="Купить пиццу"
                           onclick="window.open('${tempPizza.company.url}'); return false;"
                           class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                    />
                    <button
                            class="btn btn-link btn-sm js-add-to-compare"
                            data-id=""
                            data-category-id=""
                    >Подробнее
                    </button>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>