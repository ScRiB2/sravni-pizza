from selenium import webdriver
import psycopg2 as p2
from contextlib import closing


def get_list_price(pizza):

    console = pizza.find_element_by_class_name('size-choose-block')
    sizes = pizza.find_elements_by_class_name('size')
    prices = list()
    print(len(sizes))
    sizes[0].click()
    try:

        for size in sizes[:3]:
            size.click()
            #print(1)
            prices.append(pizza.find_element_by_class_name("price.total-price").find_element_by_tag_name('span').text)
    except:
        prices.append(str(0))
    return prices

ingridientiks = []

pizzik = []


def pizza(url, driver):
    driver.get(url)
    print("start")
    pizza_container = driver.find_element_by_class_name('items.holder').find_elements_by_class_name("item")
    print(len(pizza_container))
    for pizzas in pizza_container[:-2]:
        ingr = []
        name = pizzas.find_element_by_class_name("item-content").find_element_by_tag_name("span").text
        image = pizzas.find_element_by_class_name("image.pointer").find_element_by_tag_name("img").get_attribute("src")
        pizzas.find_element_by_class_name("item-description-wrapper").click()
        for ingridient in pizzas.find_element_by_class_name("item-description")\
                .find_element_by_class_name("ingredients").text.split(','):
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
                'company_id': 2,
                'ingridient': ingr
                }
            )
    print(len(pizzik), pizzik)
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
    url = "https://voronezh.pizzasushiwok.ru/pizza/"
    pizzas = pizza(url, driver)
    driver.quit()
    #CreateDatabase("JustForTest", "postgres", "odifus2312")
    #insertInDatabase("JustForTest", "postgres", "odifus2312", pizzas)
    return ingridientiks

def main2():
    driver = webdriver.Firefox(executable_path=r'C:\\Users\\ScRiB\\Desktop\\Firefox\\geckodriver.exe')
    driver.maximize_window()
    url = "https://voronezh.pizzasushiwok.ru/pizza/"
    pizzas = pizza(url, driver)
    driver.quit()



if __name__ == '__main__':
    main()
    ingridientiks = set(ingridientiks)
    ingridientiks = list(ingridientiks)
    print(len(ingridientiks), ingridientiks)