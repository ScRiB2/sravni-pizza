<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Webdevkin. Интернет-магазин. Каталог</title>
    <meta name="description" content="Тестовый интернет-магазин от webdevkin-a. Каталог с фильтрами и сортировками" />
    <link href='https://fonts.googleapis.com/css?family=Ubuntu:400,700&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="components/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body data-page="catalogDB">
    <div class="container">
        <h1>Фильтры в каталоге. Интернет-магазин shop.webdevkin.ru</h1>
        <br />
        Статьи на webdevkin-e. Читаем, разбираем, комментируем...
        <br />
        <br />
        <a href="https://webdevkin.ru/posts/frontend/korzina-dlya-internet-magazina-na-fronte-ili-pishem-modulnyij-javascript" target="_blank">
            "Корзина для интернет-магазина на фронте или Пишем модульный javascript".
        </a>
        <br />
        <a href="https://webdevkin.ru/posts/backend/internet-magazin-realizuem-oformlenie-zakaza-na-kliente-i-servere" target="_blank">
            "Реализуем отправку заказа в интернет-магазине на клиенте и сервере".
        </a>
        <br />
        <a href="https://webdevkin.ru/posts/frontend/kak-dobavit-dostavku-v-internet-magazine" target="_blank">
            "Как добавить доставку в интернет-магазине".
        </a>
        <br />
        <a href="https://webdevkin.ru/posts/frontend/filtryi-i-sortirovka-v-internet-magazine-na-ajax-php-mysql" target="_blank">
            "Фильтры и сортировка в интернет-магазине на ajax-php-mysql. Серия уроков".
        </a>
        <br />
        <a href="https://webdevkin.ru/posts/frontend/sravnenie-tovarov-v-internet-magazine" target="_blank">
            "Сравнение товаров в интернет-магазине".
        </a>
        <br />
        <a href="https://webdevkin.ru/posts/frontend/postranichnaya-navigacziya" target="_blank">
            "Постраничная навигация".
        </a>
        <br />
        <br />
        <br />
        <ul class="nav nav-pills">
            <li><a href="/">Каталог</a></li>
            <li class="active"><a href="catalog.jsp">Каталог с фильтрами</a></li>
            <li><a href="catalog-pag.html">Каталог с пагинацией</a></li>
            <li id="compare-tab"><a href="compare.html">Сравнение товаров<span class="badge"></span></a></li>
            <li><a href="cart.html">Корзина<span id="total-cart-count" class="badge"></span></a></li>
            <li><a href="order.html">Оформление заказа</a></li>
        </ul>
        <br />
        <div id="filters" class="col-md-12">
            <div class="btn-group">
                <button type="button" data-category="0" class="btn btn-default active js-category">Все категории</button>
                <button type="button" data-category="1" class="btn btn-default js-category">Ноутбуки</button>
                <button type="button" data-category="2" class="btn btn-default js-category">Смартфоны</button>
                <button type="button" data-category="3" class="btn btn-default js-category">Видеокарты</button>
            </div>
            <br />
            <br />
            <form id="filters-form" role="form">
                <div class="col-md-4">
                    <h4>Бренды</h4>
                    <div id="brands">
                        <div class="checkbox"><label><input type="checkbox" name="brands[]" value="1"> Apple</label></div>
                        <div class="checkbox"><label><input type="checkbox" name="brands[]" value="2"> Samsung</label></div>
                        <div class="checkbox"><label><input type="checkbox" name="brands[]" value="3"> Lenovo</label></div>
                        <div class="checkbox"><label><input type="checkbox" name="brands[]" value="4"> Что-то еще</label></div>
                    </div>
                </div>
                <div class="col-md-4">
                    <h4>Фильтр по ценам</h4>
                    <div id="prices-label">0 - 0 руб.</div>
                    <br />
                    <input type="hidden" id="min-price" name="min_price" value="5000">
                    <input type="hidden" id="max-price" name="max_price" value="50000">
                    <div id="prices"></div>
                </div>
                <div class="col-md-4">
                    <h4>Сортировка</h4>
                    <br />
                    <select id="sort" name="sort" class="form-control">
                        <option value="price_asc">По цене, сначала дешевые</option>
                        <option value="price_desc">По цене, сначала дорогие</option>
                        <option value="rating_desc">По популярности</option>
                        <option value="good_asc">По названию, A-Z</option>
                        <option value="good_desc">По названию, Z-A</option>
                    </select>
                </div>
            </form>
        </div>
        <br />
        <br />
        <ul id="goods" class="col-md-12">
            <img src="img/loading.gif" alt="" />
        </ul>
    </div>

    <script id="goods-template" type="text/template">
        <% _.each(goods, function(item) { %>
        <li class="small-good-item row">
            <div class="col-md-2">
                <img class="small-good-item__img" src="img/goods/<%= item.photo %>" />
            </div>
            <div class="col-md-10">
                <div class="small-good-item__id">Артикул <%= item.good_id %></div>
                <div class="small-good-item__name"><%- item.good %> (рейтинг <%= item.rating %>)</div>
                <div class="small-good-item__brand">Бренд: <%- item.brand %></div>
                <div class="small-good-item__price"><%= item.price %> руб.</div>
                <button
                    class="small-good-item__btn-add btn btn-info btn-sm js-add-to-cart"
                    data-id="<%= item.good_id %>"
                    data-name="<%- item.good %>"
                    data-price="<%= item.price %>"
                >Добавить в корзину</button>
                <button
                    class="btn btn-link btn-sm js-add-to-compare"
                    data-id="<%= item.good_id %>"
                    data-category-id="<%= item.category_id %>"
                >Добавить к сравнению</button>
            </div>
        </li>
        <% }); %>
    </script>

    <script id="brands-template" type="text/template">
        <% _.each(brands, function(item) { %>
        <div class="checkbox"><label><input type="checkbox" name="brands[]" value="<%= item.id %>"> <%= item.brand %></label></div>
        <% }); %>
    </script>

    <script src="js/vendor/jquery.min.js" type="text/javascript"></script>
    <script src="js/vendor/jquery.cookie.js" type="text/javascript"></script>
    <script src="components/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
    <script src="js/vendor/underscore.min.js" type="text/javascript"></script>
    <script src="js/modules/catalogDB.js" type="text/javascript"></script>
    <script src="js/modules/cart.js" type="text/javascript"></script>
    <script src="js/modules/compare.js" type="text/javascript"></script>
    <script src="js/modules/main.js" type="text/javascript"></script>
</body>
</html>