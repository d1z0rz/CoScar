from urllib import request
from bs4 import BeautifulSoup
import pickle
import time
file = open('database.txt','w')
count = 0

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

def model_parser(manufacturer_link,manufacturer_name):
    url = BASE_URL + manufacturer_link
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html,'html.parser')
    for modeless in soup.findAll("a",{"class":"modeli"}):
        model_link = modeless['href']
        model = modeless.select('span')[0].get_text()
        body_parser(model_link,model,manufacturer_link,manufacturer_name)

def body_parser(link,model,manufacturer,manufacturer_name):
    global file, count
    body_series = []
    body_engines_names = []
    body_engine_consumption = []
    url = BASE_URL + link
    content = request.urlopen(url)
    raw_html = content.read()
    soup = BeautifulSoup(raw_html, 'html.parser')
    for series in soup.find_all('h2'):
        body_series.append(series.get_text())
        ##print(series.get_text())
    for engine_for_body in soup.find_all(class_='carData colorbl'):
        names = []
        consumption = []
        for type in engine_for_body.find_all('a', href = True):
            names.append(type.get_text())
            consumption.append(engine_consumption(type['href']))
        body_engines_names.append(names)
        body_engine_consumption.append(consumption)
    for j in range(len(body_series)):
        for i in range(len(body_engines_names[j])):
            file.write(str([manufacturer_name,body_series[j], body_engines_names[j][i],body_engine_consumption[j][i]])+'\n')
            count += 1
            print(count)

def engine_consumption(link):
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
            consumption = float(''.join(in_words).split('-')[0])
    if consumption == 0:
        return 10
    else:
        return consumption


if __name__ == '__main__':
    main()
