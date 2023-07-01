from flask import Flask, render_template, request, jsonify
application = app = Flask(__name__)
# app = Flask(__name__)

import requests
from bs4 import BeautifulSoup

from pymongo import MongoClient
client = MongoClient('mongodb+srv://sparta:test@cluster0.a0mnxnt.mongodb.net/?retryWrites=true&w=majority')
db = client.dbsparta


@app.route('/')
def home():
    return render_template('index.html')

@app.route("/movie", methods=["POST"])
def movie_post():
    url_receive = request.form['url_give']              # 팝업창에서 내가 붙여넣을 주소
    comment_receive = request.form['comment_give']      # 내가 쓸 코멘트
    star_receive = request.form['star_give']      # 내가 쓸 코멘트

    # 크롤링 필요함
    headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
    data = requests.get(url_receive,headers=headers)  # url_receive 설정함

    soup = BeautifulSoup(data.text, 'html.parser')

    # meta 태그에 property가 ogtitle인것을 가져오는 명령문
    ogtitle = soup.select_one('meta[property="og:title"]')['content']  # 타이틀
    ogimg = soup.select_one('meta[property="og:image"]')['content']  # 이미지
    ogdesc = soup.select_one('meta[property="og:description"]')['content']  # description
    
    # 위의 데이터를 데이터베이스에 넣기
    doc = {
        'title': ogtitle,
        'image':ogimg,
        'description': ogdesc,
        'comment': comment_receive,
        'star':star_receive                           
        }
    db.movies.insert_one(doc)

    return jsonify({'msg':'저장완료!'})


# 서버에서 할 일: 영화를 전부 내려주기
@app.route("/movie", methods=["GET"])
def movie_get():
    all_movies = list(db.movies.find({},{'_id':False}))
    return jsonify({'result':all_movies})

if __name__ == '__main__':
    app.run()
    # ('0.0.0.0', port=5000, debug=True)