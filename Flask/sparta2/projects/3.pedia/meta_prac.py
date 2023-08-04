import requests
from bs4 import BeautifulSoup

URL = 'https://movie.daum.net/moviedb/main?movieId=161806'
headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get(URL,headers=headers)
soup = BeautifulSoup(data.text, 'html.parser')

# 여기에 코딩을 해서 meta tag를 먼저 가져와보겠습니다.

ogtitle = soup.select_one('meta[property="og:title"]')['content']
ogdesc = soup.select_one('meta[property="og:description"]')['content']
ogimage = soup.select_one('meta[property="og:image"]')['content']

print(ogimage,ogtitle,ogdesc)