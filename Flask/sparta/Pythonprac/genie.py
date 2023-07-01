import requests
from bs4 import BeautifulSoup

URL = "https://www.genie.co.kr/chart/top200?ditc=M&rtm=N&ymd=20230101"
headers = {'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get(URL,headers=headers)
soup = BeautifulSoup(data.text, 'html.parser')
# 지니뮤직의 1~50위 곡을 스크래핑 해보세요.
# 순위 / 곡 제목 / 가수를 스크래핑 하면 됩니다.
lis = soup.select("#body-content > div.newest-list > div > table > tbody > tr")
for a in lis:
    rank = a.select_one('td.number').text[0:2].strip()
    title = a.select_one('td.info > a.title.ellipsis').text.strip()
    artist = a.select_one('td.info > a.artist.ellipsis').text
    print(rank,title,artist)
