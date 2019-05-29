<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Сравни пиццу</title>
    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,700&subset=latin,cyrillic-ext' rel='stylesheet'
          type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body data-page="catalogDB">
<div class="container">
    <br/>
    <h1 class="text-center"><big><strong>Сравни пиццу</strong></big></h1>
    <br/>
    <blockquote class="pull-right">
        <h5>Жизнь - это в основном боль и борьба. В остальном - это любовь и пицца...</h5>
        <small><i>Бенедикт Смит</i></small>
    </blockquote>
    <form:form action="" modelAttribute="filters" id="filters-form" role="form" method="post">
        <div class="row">
            <security:authorize access="isAuthenticated()">
                <h4>Здравствуйте, <security:authentication property="principal.username"/></h4>
            </security:authorize>
            <ul class="list-inline">
                <security:authorize access="hasRole('USER')">
                    <input type="button" value="Личный кабинет"
                           onclick="window.location.href='/lk'; return false;"
                           class="small-good-item__btn-add btn btn-primary btn-sm"
                    />
                </security:authorize>

                <input type="button" value="Админка"
                       onclick="window.location.href='/pizza/list'; return false;"
                       class="small-good-item__btn-add btn btn-danger btn-sm"
                />

                <security:authorize access="isAnonymous()">
                    <input type="button" value="Войти"
                           onclick="window.location.href='/login'; return false;"
                           class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                    />
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <input type="button" value="Выйти"
                           onclick="window.location.href='<c:url value="/logout"/>'; return false;"
                           class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                    />
                </security:authorize>

            </ul>
            <div class="col-md-3 col-md-offset-9">
                <div class="form-group">
                    <form:select path="sort" id="sort" name="sort" class="form-control">
                        <form:option value="0">Cначала дешевые</form:option>
                        <form:option value="1">Cначала дорогие</form:option>
                        <form:option value="2">По названию, А-Я</form:option>
                        <form:option value="3">По названию, Я-А</form:option>
                    </form:select>
                </div>
            </div>
        </div>
        <div id="filters" class="col-md-4">
            <div class="row">
                <div>
                    <h3>Компании</h3>
                    <div id="brands">
                        <c:forEach var="company" items="${companies}">
                            <div class="form-check"><label><form:checkbox path="companiesName"
                                                                          value="${company.name}"/> ${company.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <h3>Цена</h3>
                    <div id="prices-label">
                        Минимальная: <form:input path="minPrice" id="min-price" name="min_price"
                                                 value="${filters.minPrice}"/>
                        Максимальная: <form:input path="maxPrice" id="max-price" name="max_price"
                                                  value="${filters.maxPrice}"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <h3>Размеры</h3>
                    <div>
                        <c:forEach var="size" items="${sizes}">
                            <div class="form-check"><label><form:checkbox path="sizes"
                                                                          value="${size.name}"/> ${size.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row">
                <div>
                    <h3>Категории</h3>
                    <div>
                        <c:forEach var="category" items="${categories}">
                            <div class="form-check"><label><form:checkbox path="categoriesName"
                                                                          value="${category.name}"/> ${category.name}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row"><input type="submit" value="Подтвердить"
                                    class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
            />
                <input type="button" value="Сбросить"
                       onclick="window.location.href='/'; return false;"
                       class="small-good-item__btn-add btn btn-danger btn-sm"
                />
            </div>

        </div>
    </form:form>
    <br/>
    <br/>
    <ul id="goods" class="col-md-8">
        <c:forEach var="tempPizza" items="${pizzas}">
            <li class="small-good-item row">
                <div class="col-md-4">
                    <img class="small-good-item__img" src="${tempPizza.image}"/>
                </div>
                <div class="col-md-4">
                    <div class="small-good-item__name">${tempPizza.name} </div>
                    <div class="small-good-item__id">Ингредиенты:
                        <c:forEach var="ingredient"
                                   items="${tempPizza.ingredients}">${ingredient.name}, </c:forEach></div>
                    <div class="small-good-item__brand">Компания: <i>${tempPizza.company.name}</i></div>
                    <div class="small-good-item__id">Размер: ${tempPizza.size.name} </div>
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