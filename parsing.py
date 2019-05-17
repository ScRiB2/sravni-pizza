import time

import requests
from bs4 import BeautifulSoup as bs

header = {"accept": "*/*", "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                                         "(KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36"}
base_url = "http://zharpizza.ru/voronezh/"


def pizza(base_url, headers):
    pizzas = []
    session = requests.Session()
    request = session.get(base_url, headers=header)
    if request.status_code == 200:
        print('Ok')
        start = time.time()
        soup = bs(request.content, 'lxml')
        divs = soup.find_all('div', attrs={'class': 'pizza-flex-item'})
        print(len(divs))
        for i, div in enumerate(divs[:11]):
            title = div.find('div', attrs={'class': 'goods-title'}).text.strip()
            ingridients = div.find('div', attrs={'class': 'goods-description'}).text.strip().split(',')
            price = div.find('div', attrs={'class': 'goods-price'}).text.strip()
            if(i<8):
                price = int(price[:-1]) * 8
            else:
                price = int(price[:-1])
            image = str(div.find('div', attrs={'class': "goods-image goods-image-pizza"}).find('img'))
            image = 'http://zharpizza.ru' + image[image.find('src') + 5 : -3]
            print(image)
            print(price)
            print(title)
            print(ingridients)
            pizzas.append({
                'title': title,
                'ingridients': ingridients,
                'price': price,
                'image': image
            })
        print(len(pizzas))
    else:
        print("ERROR")


if __name__ == "__main__":
    pizza(base_url, header)
