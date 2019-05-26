from selenium import webdriver
import psycopg2 as p2
from contextlib import closing

def write_csv(data):
    with open('DodoPizzas.csv', 'a', newline="", encoding='utf-8') as f:
        writer = csv.writer(f)
        print("write " + data['name'])

        writer.writerow([data['name'],
                         data['image'],
                         data['ingridients'],
                         data['prices']]
                        )


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


def pizza(url, driver):
    driver.get(url)
    print("start")
    pizzas_div = driver.find_element_by_class_name('goods-content.section-goods-content.js__goods-list-ctn')
    pizza_container = pizzas_div.find_elements_by_class_name('goods-card.goods-card-mobile.js__goods-wrap')
    print(pizza_container)
    pizzik = []
    for pizzas in pizza_container:
        name = pizzas.find_element_by_class_name("goods-card-title").text[:-3]
        image = pizzas.find_element_by_class_name("goods-card-img-cnt.js__goods-image.image-link").get_attribute("href")
        ingridients = pizzas.find_element_by_class_name("goods-card-composition").text
        prices = get_list_price(pizzas)
        pizzik.append(
            {
            'name': name,
            'image': image,
            'ingridients': ingridients,
            'prices': ','.join(prices)
            }
        )
    pizzik = pizzik[:-9]
    del pizzik[-3]
    return pizzik
def CreateDatabase(yourDatabase, user, password):
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

def insertInDatabase(yourDatabase, user, password, pizzas):
    with closing(p2.connect(dbname="{}".format(yourDatabase), user="{}".format(user),
                            password="{}".format(password), host="localhost")) as con:
        with con.cursor() as cursor:
            con.autocommit = True
            cursor.executemany("INSERT INTO pizza(name, ingridients, price, image) VALUES "
                               "(%(name)s, %(ingridients)s, %(prices)s, %(image)s)", pizzas)

def main():
    driver = webdriver.Firefox(executable_path=r'C:\\Users\\ScRiB\\Desktop\\Firefox\\geckodriver.exe')
    driver.maximize_window()
    url = "https://yummypizza.ru/sections/pitstsy"
    pizzas = pizza(url, driver)
    driver.quit()
    CreateDatabase("JustForTest", "postgres", "odifus2312")
    insertInDatabase("JustForTest", "postgres", "odifus2312", pizzas)

if __name__ == '__main__':
    main()