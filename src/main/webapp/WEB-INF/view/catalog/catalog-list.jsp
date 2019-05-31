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
    <script>
        function sortPrice(x) {
            switch (x) {
                case "1" :
                    nodeList = document.querySelectorAll('li');
                    var itemsArray = [];
                    var parent = nodeList[0].parentNode;
                    for (var i = 0; i < nodeList.length; i++) {
                        itemsArray.push(parent.removeChild(nodeList[i]));
                    }
                    //for (var i=0; i<itemsArray.length; i++)
                    //  alert(itemsArray[i].getAttribute('data-price'));
                    itemsArray.sort(function (nodeA, nodeB) {
                        var numberA = parseInt(nodeA.getAttribute('data-price'));
                        var numberB = parseInt(nodeB.getAttribute('data-price'));
                        if (numberA < numberB) return -1;
                        if (numberA > numberB) return 1;
                        return 0;
                    }).forEach(function (node) {
                        parent.appendChild(node);
                    });
                    break;
                case "2" :
                    nodeList = document.querySelectorAll('li');
                    var itemsArray = [];
                    var parent = nodeList[0].parentNode;
                    for (var i = 0; i < nodeList.length; i++) {
                        itemsArray.push(parent.removeChild(nodeList[i]));
                    }
                    //for (var i=0; i<itemsArray.length; i++)
                    //  alert(itemsArray[i].getAttribute('data-price'));
                    itemsArray.sort(function (nodeA, nodeB) {
                        var numberA = parseInt(nodeA.getAttribute('data-price'));
                        var numberB = parseInt(nodeB.getAttribute('data-price'));
                        if (numberA > numberB) return -1;
                        if (numberA < numberB) return 1;
                        return 0;
                    }).forEach(function (node) {
                        parent.appendChild(node);
                    });
                    break;
                case "3" :
                    nodeList = document.querySelectorAll('li');
                    var itemsArray = [];
                    var parent = nodeList[0].parentNode;
                    for (var i = 0; i < nodeList.length; i++) {
                        itemsArray.push(parent.removeChild(nodeList[i]));
                    }
                    //for (var i=0; i<itemsArray.length; i++)
                    //  alert(itemsArray[i].getAttribute('data-price'));
                    itemsArray.sort(function (nodeA, nodeB) {
                        var strA = nodeA.getAttribute('data-name');
                        var strB = nodeB.getAttribute('data-name');
                        if (strA < strB) return -1;
                        if (strA > strB) return 1;
                        return 0;
                    }).forEach(function (node) {
                        parent.appendChild(node);
                    });
                    break;
                case "4" :
                    nodeList = document.querySelectorAll('li');
                    var itemsArray = [];
                    var parent = nodeList[0].parentNode;
                    for (var i = 0; i < nodeList.length; i++) {
                        itemsArray.push(parent.removeChild(nodeList[i]));
                    }
                    //for (var i=0; i<itemsArray.length; i++)
                    //  alert(itemsArray[i].getAttribute('data-price'));
                    itemsArray.sort(function (nodeA, nodeB) {
                        var strA = nodeA.getAttribute('data-name');
                        var strB = nodeB.getAttribute('data-name');
                        if (strA > strB) return -1;
                        if (strA < strB) return 1;
                        return 0;
                    }).forEach(function (node) {
                        parent.appendChild(node);
                    });
                    break;
            }
        }

    </script>
</head>
<body>
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
                <security:authorize access="hasRole('ADMIN')">
                    <input type="button" value="Админка"
                           onclick="window.location.href='/pizza/list'; return false;"
                           class="small-good-item__btn-add btn btn-danger btn-sm"
                    />
                </security:authorize>
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
                    <select onchange="sortPrice(value)" on class="form-control">
                        <option value="0">-</option>
                        <option value="1">Cначала дешевые</option>
                        <option value="2">Cначала дорогие</option>
                        <option value="3">По названию, А-Я</option>
                        <option value="4">По названию, Я-А</option>
                    </select>
                </div>
            </div>
        </div>
        <div id="filters" class="col-md-4">
            <div class="row">
                <div>
                    <h3>Компании</h3>
                    <div>
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
                    <div class="small-good-item__brand">
                        Мин.: <form:input path="minPrice" id="min-price" name="min_price"
                                          value="${filters.minPrice}"/>
                    </div>
                    <div class="small-good-item__brand">
                        Макс.: <form:input path="maxPrice" id="max-price" name="max_price"
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
            <li class="small-good-item row" style="border-radius: 6px;" data-price="${tempPizza.price}"
                data-name="${tempPizza.name}">
                <div class="col-md-4">
                    <img class="small-good-item__img" src="${tempPizza.image}"/>
                </div>
                <div class="col-md-8">
                    <div class="small-good-item__name">${tempPizza.name} </div>
                    <div class="small-good-item__ingredients">Ингредиенты:
                        <c:forEach var="ingredient"
                                   items="${tempPizza.ingredients}">${ingredient.name}; </c:forEach></div>
                    <div class="small-good-item__brand" style="padding: 10px 0px 0px">
                        Компания: ${tempPizza.company.name}</div>

                    <div class="small-good-item__size" style="padding: 10px 0px 0px">
                        Размер: ${tempPizza.size.name}</div>

                    <div class="small-good-item__price" style="padding: 5px 0px 0px">${tempPizza.price} ₽</div>
                    <div class="button " style="margin: 10px 0px 0px">
                        <input type="button" id="byu-button" value="Купить пиццу"
                               onclick="window.open('${tempPizza.company.url}'); return false;"
                               class="small-good-item_buy_button"
                        />
                    </div>
                    <security:authorize access="hasRole('ADMIN')">
                        <div style="margin: 10px 0px 0px">
                            <c:url var="updateLink" value="/pizza/update">
                                <c:param name="pizzaId" value="${tempPizza.id}"/>
                            </c:url>
                            <input type="button" value="Обновить"
                                   onclick="window.location.href='${updateLink}'; return false;"
                                   class="small-good-item__btn-add btn btn-primary btn-sm"
                            />
                            <c:url var="deleteLink" value="/pizza/delete">
                                <c:param name="pizzaId" value="${tempPizza.id}"/>
                            </c:url>
                            <input type="button" value="Удалить"
                                   onclick="if (!(confirm('Вы точно хотите удалить эту пиццу?'))) return false;
                                           window.location.href='${deleteLink}'; return false;"
                                   class="small-good-item__btn-add btn btn-danger btn-sm"
                            />
                        </div>
                    </security:authorize>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>