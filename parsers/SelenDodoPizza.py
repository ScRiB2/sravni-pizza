from selenium import webdriver
import psycopg2 as p2
from contextlib import closing


def get_list_price(pizza):
    controls = pizza.find_element_by_class_name('product__controls')
    sizes = controls.find_elements_by_class_name('product__size-control-item')
    prices = list()
    try:
        for size in sizes:
            size.click()
            prices.append(controls.find_element_by_class_name('money__value').text)
    except:
        prices.append(str(0))
    return prices


ingridientiks = []
pizzik = []

def pizza(url, driver):
    driver.get(url)
    print("start")
    pizzas_div = driver.find_element_by_id('pizzas')
    pizza_container = pizzas_div.find_elements_by_class_name('menu__section-row-outer')
    for pizzas in pizza_container:

        pizzass = pizzas.find_elements_by_class_name(
            'menu__section-product.menu__section-product_4-1.product')
        print(len(pizzass))
        for pizza in pizzass:
            image = pizza.find_element_by_class_name('product__image').find_element_by_class_name(
                'product__image-img').get_attribute('src')
            name = pizza.find_element_by_class_name('product__name').text.strip()
            ingr = []
            print('s')
            for ingridient in  pizza.find_element_by_class_name(
                'product__description.product__description_meta').text.split(','):
                print(ingridient)
                if ' и ' in ingridient:
                    for ing in ingridient.split(' и '):
                        ingridientiks.append(ing.lower().strip())
                        ingr.append(ing.lower().strip())
                else:
                    ingridientiks.append(ingridient.lower().strip())
                    ingr.append(ingridient.lower().strip())
            prices = get_list_price(pizza)
            prices.sort()
            print()
            print(ingr)
            print()
            print(1, prices)
            for price, size in zip(prices, ["STANDART", "MEDIUM", "BIG"]):

                pizzik.append(
                    {
                        'name': name,
                        'image': image,
                        'prices': price,
                        'size': size,
                        'company_id': 1,
                        'ingridient': ingr
                    }
                )
    del(pizzik[4])
    print(pizzik)
    return pizzik


def insertInDatabase(yourDatabase, user, password, pizzas):
    with closing(p2.connect(dbname="{}".format(yourDatabase), user="{}".format(user),
                            password="{}".format(password), host="localhost")) as con:
        with con.cursor() as cursor:
            con.autocommit = True
            cursor.executemany("INSERT INTO pizza(name, ingridients, price, image) VALUES "
                               "(%(name)s, %(ingridients)s, %(prices)s, %(image)s)", pizzas)


def main():
    driver = webdriver.Firefox(executable_path=r'C:\\Users\\dzhal\\geckodriver.exe')
    driver.maximize_window()
    url = "https://dodopizza.ru/voronezh#pizzas"
    pizzas = pizza(url, driver)
    driver.quit()
    ingridientiks.remove("соберите свою пиццу 35 см с двумя разными вкусами")
#    insertInDatabase("JustForTest", "postgres", "odifus2312", pizzas)
    return ingridientiks

if __name__ == '__main__':
    main()
    ingridientiks = set(ingridientiks)
    ingridientiks = list(ingridientiks)
    print(len(ingridientiks), ingridientiks)
