# hello.py

$ python -m venv venv
$ pip install requests  ( requests  패키지 설치)

# requests 사용법
r = requests.get('URL')
rjson = r.json()
<!-- ###################################### -->
$ pip install bs4

# bs4의 기본코드- 크롤링

import requests
from bs4 import BeautifulSoup

URL = "<URL입력하기>"

headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get(URL, headers=headers)

soup = BeautifulSoup(data.text, 'html.parser')

<!-- ###################################### -->
# monggoDB 사용하기
$ pip install dnspython
$ pip install pymongo

# <기본코드>
from pymongo import MongoClient
client = MongoClient('여기에 URL 입력(mongoDB주소)')
db = client.dbsparta

# 저장 - 예시
doc = {'name':'bobby','age':21}
db.users.insert_one(doc)

# 한 개 찾기 - 예시
user = db.users.find_one({'name':'bobby'})

# 여러개 찾기 - 예시 ( _id 값은 제외하고 출력)
all_users = list(db.users.find({},{'_id':False}))

# 바꾸기 - 예시
db.users.update_one({'name':'bobby'},{'$set':{'age':19}})

# 지우기 - 예시
db.users.delete_one({'name':'bobby'})
