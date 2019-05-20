import csv
from selenium import webdriver


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
    controls = pizza.find_element_by_class_name('product__controls')
    sizes = controls.find_elements_by_class_name('product__size-item')
    prices = list()
    try:
        for size in sizes:
            size.click()
            prices.append(controls.find_element_by_class_name('money__value').text)
    except:
        prices.append(str(0))
    return prices


def pizza(url, driver):
    driver.get(url)
    print("start")
    pizzas_div = driver.find_element_by_id('pizzas')
    pizza_container = pizzas_div.find_elements_by_class_name('menu__section-row-outer')

    for pizzas in pizza_container:
        pizzas = pizzas.find_elements_by_class_name(
            'product__inner.product__inner_meta.product__inner_meta-with-controls')
        for pizza in pizzas:
            image = pizza.find_element_by_class_name('product__image').find_element_by_class_name(
                'product__image-img').get_attribute('src')
            name = pizza.find_element_by_class_name('product__name').text.strip()
            ingridients = pizza.find_element_by_class_name(
                'product__description.product__description_meta').text.strip().split(',')

            prices = get_list_price(pizza)

            pizza = {'image': image,
                     'name': name,
                     'ingridients': ingridients,
                     'prices': prices}
            write_csv(pizza)


def main():
    driver = webdriver.Firefox(executable_path=r'C:\\Users\\ScRiB\\Desktop\\Firefox\\geckodriver.exe')
    url = "https://dodopizza.ru/voronezh#pizzas"
    pizza(url, driver)
    driver.quit()


if __name__ == '__main__':
    main()
