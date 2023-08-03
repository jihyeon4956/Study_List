from flask import Flask, render_template, request, jsonify
# API를 만들떄 render_template, request, jsonify 임포트 해줘야함
app = Flask(__name__)

@app.route('/')
def home():
   return render_template('index.html')

@app.route('/test', methods=['GET'])
def test_get():
   title_receive = request.args.get('title_give')
   print(title_receive)
   return jsonify({'result':'success', 'msg': '이 요청은 GET!'})
# /test로 GET요청이 들어왔을 떄 title_give데이터가 있으면 title_receive라는 변수에 넣자는 의미
# 그리고 리턴을 result는 success, msg는 '이 요청은 GET!'으로 내려준다


@app.route('/test', methods=['POST'])
def test_post():
   title_receive = request.form['title_give']
   print(title_receive)
   return jsonify({'result':'success', 'msg': '이 요청은 POST!'})

if __name__ == '__main__':  
   app.run('0.0.0.0',port=5000,debug=True)

   