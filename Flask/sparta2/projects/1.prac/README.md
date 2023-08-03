# 플라스크 환경 설정
$ pip install flask

# 기본파일
from flask import Flask
app = Flask(__name__)

@app.route('/')
def home():
   return 'This is Home!'

if __name__ == '__main__':  
   app.run('0.0.0.0',port=5000,debug=True)

# 템플릿 사용방법
import에 render_template 추가 후
return render_template('html 경로 입력')