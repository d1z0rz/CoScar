from urllib import request
from bs4 import BeautifulSoup
import pickle
import time
file = open('database.csv','w')
count = 0
manufacturer_id=1
model_id=1
series_id=1
engine_id=0

BASE_URL = 'https://www.auto-data.net'
SETUP = '/en/allbrands'

def main():
    start = time.time()
    global file
    manufacturer_parser()
    finish = time.time()
    print(start-finish)
    file.close()

def manufacturer_parser():
    global manufacturer_id
    manufacturers_links = []
    manufacturers = []
    url = BASE_URL+SETUP
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html, 'html.parser')
    for div in soup.find_all('a', href=True):
        element = ('URL', div['href'])
        manufaturer_name = div.get_text()
        if len(element[1]) in range (14,27):
            manufacturers_links.append(element[1])
            manufacturers.append(manufaturer_name)
    manufacturers_links.pop()
    manufacturers_links.pop()
    manufacturers_links.pop(0)
    manufacturers.pop()
    manufacturers.pop()
    manufacturers.pop(0)

    for row in range(len(manufacturers_links)):
        model_parser(manufacturers_links[row],manufacturers[row])
        manufacturer_id +=1

def model_parser(manufacturer_link,manufacturer_name):
    global model_id
    url = BASE_URL + manufacturer_link
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html,'html.parser')
    for modeless in soup.findAll("a",{"class":"modeli"}):
        model_link = modeless['href']
        model = modeless.select('span')[0].get_text()
        body_parser(model_link,model,manufacturer_link,manufacturer_name)
        model_id +=1

def body_parser(link,model,manufacturer,manufacturer_name):
    global file, count, manufacturer_id, model_id, series_id, engine_id
    body_series = []
    model = 0
    url = BASE_URL + link
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html, 'html.parser')
    for series in soup.find_all('h2'):
        body_series.append(series.get_text())
    for engine_for_body in soup.find_all(class_='carData colorbl'):
        for type in engine_for_body.find_all('a', href = True):
            names = type.get_text()
            consumption = engine_consumption(type['href'])
            count += 1
            ##file.write(
            end = (str([manufacturer_id,manufacturer_name,model_id,body_series[model],series_id,names,engine_id,consumption])+'\n')
            file.write(end)
            print(count)
        model += 1
        series_id +=1

def engine_consumption(link):
    global engine_id
    engine_id +=1
    data = []
    consumption = 0
    trash = ' l/100 km.'
    url = BASE_URL + link
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html, 'html.parser')
    for object in soup.find_all('td'):
        data.append(object.get_text())
    for i in range(len(data)):
        if 'Fuel consumption (economy) - combined ' == data[i]:
            in_words = list(data[i+1])
            del in_words [-11:-1]
            consumption = float(''.join(in_words).split('-')[0].replace('..','.'))
    if consumption == 0:
        return 10
    else:
        return consumption


if __name__ == '__main__':
    main()
