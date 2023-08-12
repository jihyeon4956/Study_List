from pymongo import MongoClient
client = MongoClient('mongodb+srv://sparta:test@cluster0.a0mnxnt.mongodb.net/?retryWrites=true&w=majority')
db = client.dbsparta


# 저장 - 예시
doc = {'name':'bobby','age':21}
db.users.insert_one(doc)


# 한 개 찾기 - 예시
user = db.users.find_one({'name':'bobby'})


# 여러개 찾기 - 예시 ( _id 값은 제외하고 출력)
all_users = list(db.users.find({},{'_id':False}))

# 여러개 찾기 - 요소 여러개일때
# all_movies = list(db.movies.find({},{'_id':False}))
# return jsonify({'result':all_movies})


# 바꾸기 - 예시
db.users.update_one({'_id':''},{'$set':{'age':19}})


# 지우기 - 예시
db.users.delete_one({'name':'bobby'})

 