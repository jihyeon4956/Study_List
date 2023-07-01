from pymongo import MongoClient
client = MongoClient('mongodb+srv://sparta:test@cluster0.a0mnxnt.mongodb.net/?retryWrites=true&w=majority')
db = client.dbsparta

#1 평점 가져오기
Q1 = db.movies.find_one({'title': '범죄도시3'})
print(Q1['rate'])

#1과 같은 평점을 가지는 영화 제목들을 수집
target_star = Q1['rate']
Q2 = list(db.movies.find({'rate':target_star},{'_id':False}))
for a in Q2:
    print(a['title'])

db.movies.update_one({'title':'범죄도시3'},{'$set':{'rate':0}})
 