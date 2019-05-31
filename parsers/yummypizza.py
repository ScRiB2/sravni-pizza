from selenium import webdriver
import psycopg2 as p2
from contextlib import closing


def get_list_price(pizza):
    controls = pizza.find_element_by_class_name('goods-props-price-ctn')
    sizes = controls.find_elements_by_class_name('pizza-size.goods-prop-value.js__goods-model-prop')
    prices = list()
    try:
        for size in sizes:
            size.click()
            prices.append(controls.find_element_by_class_name('price.goods-price').text)
    except:
        prices.append(str(0))
    return prices


ingridientiks = []
pizzik = []

def pizza(url, driver):
    driver.get(url)
    print("start")
    pizzas_div = driver.find_element_by_class_name('goods-content.section-goods-content.js__goods-list-ctn')
    pizza_container = pizzas_div.find_elements_by_class_name('goods-card.goods-card-mobile.js__goods-wrap')


    for pizzas in pizza_container[:-9]:
        name = pizzas.find_element_by_class_name("goods-card-title").text[:-3]
        image = pizzas.find_element_by_class_name("goods-card-img-cnt.js__goods-image.image-link").get_attribute("href")
        ingr = []
        for ingridient in pizzas.find_element_by_class_name("goods-card-composition").text.split(','):
            ingridientiks.append(ingridient.lower().strip())
            ingr.append(ingridient.lower().strip())
        prices = get_list_price(pizzas)
        for price, size in zip(prices, ["STANDART", "MEDIUM", "BIG"]):

            pizzik.append(
                {
                'name': name,
                'image': image,
                'prices': price,
                'size': size,
                'company_id': 3,
                'ingridient': ingr
                }
            )
    del pizzik[-3]
    print(pizzik)
    return pizzik


'''def CreateDatabase(yourDatabase, user, password):
    with closing(p2.connect(dbname="{}".format(yourDatabase), user="{}".format(user),
                            password="{}".format(password), host="localhost")) as con:
        with con.cursor() as cursor:
            con.autocommit = True
            cursor.execute("DROP TABLE Pizza")
            try:
                cursor.execute("Create table Pizza("
                           "id SERIAL, "
                           "name varchar(50), "
                           "ingridients varchar(200),"
                           "price varchar(50),"
                           "image varchar(1000));")
            except:
                pass
'''


def insertInDatabase(yourDatabase, user, password, pizzas):
    with closing(p2.connect(dbname="{}".format(yourDatabase), user="{}".format(user),
                            password="{}".format(password), host="localhost")) as con:
        with con.cursor() as cursor:
            con.autocommit = True
            company = [{'name': 'Додо Пицца'}, {'name': 'ПиццаСушиВок'}, {'name': 'YmmY'}]
            cursor.executemany("INSERT INTO company(name) VALUES (%(name)s)", company)
            #cursor.executemany("INSERT INTO pizza(name, size, price, image, company_id) VALUES "
            #                   "(%(name)s, %(size)s, %(prices)s, %(image)s, %(company_id)s)", pizzas)



def main():
    driver = webdriver.Firefox(executable_path=r'C:\\Users\\ScRiB\\Desktop\\Firefox\\geckodriver.exe')
    driver.maximize_window()
    url = "https://yummypizza.ru/sections/pitstsy"
    pizzas = pizza(url, driver)
    driver.quit()
#    CreateDatabase("JustForTest", "postgres", "odifus2312")
#    insertInDatabase("main", "postgres", "odifus2312", pizzas)
    return ingridientiks

if __name__ == '__main__':
    main()
    ingridientiks = set(ingridientiks)
    ingridientiks = list(ingridientiks)
    print(len(ingridientiks), ingridientiks)