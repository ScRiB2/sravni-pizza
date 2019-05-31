from selenium import webdriver
from operator import itemgetter
import psycopg2 as p2
from contextlib import closing
import sys
import numpy as np
sys.path.append("C:\\Users\\ScRiB\\UltimateIdeaProjects\\SSTH.ru\\parsers")
import pizzasushiwok
import yummypizza
import SelenDodoPizza


def checkIngridient(ingridient, ingridientsInDatabase):
    for id, name, _ in ingridientsInDatabase.sort(reverse=True):
        if name in ingridient:
            return id


def insertInDatabase(yourDatabase, user, password, ingridientsResult):
    with closing(p2.connect(dbname="{}".format(yourDatabase), user="{}".format(user),
                            password="{}".format(password), host="localhost")) as con:
        with con.cursor() as cursor:
            con.autocommit = True
            category = [{'name': "фрукты"}, {'name': 'овощи'}, {'name': 'мясо'}, {'name': 'рыба'}, {'name': 'соус'},
                        {'name': 'прочее'}, {'name': 'сыр'}]
            cursor.executemany("INSERT INTO category_ingredient(name) VALUES "
                               "(%(name)s)", category)
            company = [{'name': 'Додо пицца', 'url': "https://dodopizza.ru/voronezh#pizzas"},
                       {'name': 'Сушивок пицца', 'url': "https://voronezh.pizzasushiwok.ru/pizza/"},
                       {'name': 'Ямма пицца', 'url': "https://yummypizza.ru/sections/pitstsy"}]
            cursor.executemany("INSERT INTO company(name, url) VALUES (%(name)s, %(url)s)", company)
            cursor.executemany("INSERT INTO ingredient(name, category_id) VALUES "
                               "(%(name)s, %(category_id)s)", ingridientsResult)
            cursor.executemany("INSERT INTO pizza(name, image, price, company_id, size) VALUES "
                               "(%(name)s, %(image)s , %(prices)s, %(company_id)s, %(size)s)", pizzasushiwok.pizzik)
            cursor.execute("select id from pizza order by id desc limit 1")
            last_element = cursor.fetchall()
            print("last element{}".format(last_element))
            cursor.execute("SELECT id, name FROM ingredient")
            allIngridients = cursor.fetchall()
            print("все игредиенты{}".format(allIngridients))
            pizzaAndIngredient = []
            print("len of pizza{}".format(len(pizzasushiwok.pizzik)))
            print (last_element)
            print(pizzasushiwok.pizzik)

            last_element = 1
            for pizz_id, pizza in zip(range(last_element, len(pizzasushiwok.pizzik)+1), pizzasushiwok.pizzik):
                for ingredient in pizza['ingridient']:
                    for id, name in sorted(allIngridients, key=itemgetter(1), reverse=True):
                        if name in ingredient:
                            pizzaAndIngredient.append(
                                {
                                    "ingredient_id" : id,
                                    "pizza_id" : pizz_id
                                 }
                            )
                            break

            print(pizzaAndIngredient)
            cursor.executemany("INSERT INTO pizza_ingredient(ingredient_id, pizza_id) VALUES "
                               "(%(ingredient_id)s, %(pizza_id)s)", pizzaAndIngredient)
            cursor.execute("select id from pizza order by id desc limit 1")
            last_element = cursor.fetchall()[0][0]
            pizzaAndIngredient=[]
            print()
            print('begin')
            print(last_element, last_element + len(yummypizza.pizzik)+1, yummypizza.pizzik)
            for pizz_id, pizza in zip(range(last_element+1, last_element + len(yummypizza.pizzik) + 1), yummypizza.pizzik):
                for ingredient in pizza['ingridient']:
                    for id, name in sorted(allIngridients,key=itemgetter(1), reverse=True):
                        if name in ingredient:
                            pizzaAndIngredient.append(
                                {
                                    "ingredient_id" : id,
                                    "pizza_id" : pizz_id
                                 }
                            )
                            break
            cursor.executemany("INSERT INTO pizza(name, image, price, company_id, size) VALUES "
                               "(%(name)s, %(image)s , %(prices)s, %(company_id)s, %(size)s)", yummypizza.pizzik)
            cursor.executemany("INSERT INTO pizza_ingredient(ingredient_id, pizza_id) VALUES "
                               "(%(ingredient_id)s, %(pizza_id)s)", pizzaAndIngredient)
            cursor.execute("select id from pizza order by id desc limit 1")
            last_element = cursor.fetchall()[0][0]
            pizzaAndIngredient = []
            for pizz_id, pizza in zip(range(last_element+1, last_element + len(SelenDodoPizza.pizzik) + 1), SelenDodoPizza.pizzik):
                for ingredient in pizza['ingridient']:
                    for id, name in sorted(allIngridients, key=itemgetter(1), reverse=True):
                        if name in ingredient:
                            pizzaAndIngredient.append(
                                {
                                    "ingredient_id": id,
                                    "pizza_id": pizz_id
                                }
                            )
                            break
            cursor.executemany("INSERT INTO pizza(name, image, price, company_id, size) VALUES "
                               "(%(name)s, %(image)s , %(prices)s, %(company_id)s, %(size)s)", SelenDodoPizza.pizzik)
            cursor.executemany("INSERT INTO pizza_ingredient(ingredient_id, pizza_id) VALUES "
                               "(%(ingredient_id)s, %(pizza_id)s)", pizzaAndIngredient)
    con.close()


ingridients = list(set(np.hstack([yummypizza.main(), pizzasushiwok.main(), SelenDodoPizza.main()])))
ingridients.sort()


print(pizzasushiwok.pizzik)

ingridients.remove("ананасы")
ingridients.remove("грибы шампиньоны в сливочном соусе")
ingridients.remove("пикантная пепперони")
ingridients.remove('сыр "моцарелла"')
ingridients.remove("шампиньоны св")
ingridients.remove('колбаса "пепперони"')
ingridients.remove('колбаса "салями"')
ingridients.remove('перец "халапеньо"')
ingridients.remove("перец халапеньо")
ingridients.remove("острый халапеньо")
ingridients.remove("соус сливочный")
ingridients.remove("сливочный соус")
ingridients.append('сливочный')
ingridients.append('халапеньо')
ingridients.remove('соус томатный')
ingridients.remove("томатный соус")
ingridients.append('томатный')
ingridients.remove('соус "цезарь"')
ingridients.remove('соус цезарь')
ingridients.append('цезарь')

print(len(ingridients), ingridients)

ingridientsResult = []

print("size of ingredients: {}".format(ingridients))


def workWithIngridient():
    for ingridient in ingridients:
        name = ingridient
        category_id = 0
        if 'сыр' in ingridient or 'пармезан' in ingridient or 'сулугуни' in ingridient or 'дор-блю' in ingridient\
            or 'моцарелла' in ingridient:
            category_id = 7

        elif 'соус' in ingridient or 'сливочный' in ingridient or 'томатный' in ingridient or "цезарь" in ingridient:
            category_id = 5

        elif 'лук' in ingridient or "перец" in ingridient or 'халапеньо' in ingridient:
            category_id = 2

        elif 'бекон' in ingridient or 'фарш' in ingridient or 'ветчина' in ingridient or 'цыпленок' in ingridient\
            or 'каперсы' in ingridient or 'колбаса' in ingridient or 'салями' in ingridient or 'колбаски' in ingridient\
                or 'грудка' in ingridient or 'говядина' in ingridient or "пепперони" in ingridient:
            category_id = 3

        elif 'огурцы' in ingridient or 'томаты' in ingridient or 'салат' in ingridient or 'помидоры' in ingridient or 'огурчики' in ingridient\
                or 'зелень' in ingridient or 'шампиньоны' in ingridient or 'руккола' in ingridient or 'дольки картофеля' in ingridient\
                    or 'чеснок' in ingridient or 'базилик' in ingridient:
            category_id = 2

        elif 'курица' in ingridient or 'свинина' in ingridient or 'краб' in ingridient or 'чоризо' in ingridient:
            category_id = 3

        elif 'мидии' in ingridient or 'креветки' in ingridient or 'окунь' in ingridient or 'семга' in ingridient or 'лосось' in ingridient \
                or 'лосось' in ingridient or 'кальмары' in ingridient or 'осьминоги' in ingridient:
            category_id = 4

        elif 'брусника' in ingridient or 'маслины' in ingridient or 'ананас' in ingridient:
            category_id = 1

        else:
            category_id = 6

        ingridientsResult.append(
            {'name': name,
             'category_id': category_id}
        )
    return ingridientsResult


if __name__ == '__main__':
    insertInDatabase("new", "postgres", "root", workWithIngridient())