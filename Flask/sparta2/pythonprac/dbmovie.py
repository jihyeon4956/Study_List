from pymongo import MongoClient
import requests
from bs4 import BeautifulSoup

client = MongoClient('여기에 URL 입력(mongoDB주소)')
db = client.dbsparta


#부당거래 연령가를 18세 이상으로 바꾸기

db.movies2.update_one({'title':'부당거래'},{'$set':{'age':'18세 이상 관람가'}})

#하모니와 같은 연령가의 영화 모두 찾기
# movie = db.movies2.find_one({'title':'하모니'})
# age = movie['age']

# movies = list(db.movies2.find({'age':age},{'_id':False}))
# for m in movies:
#     print(m['title'])

