package com.sparta.myselectshop.naver.service;


import com.sparta.myselectshop.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "NAVER API")
@Service
public class NaverApiService {

    private final RestTemplate restTemplate;

    public NaverApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();  // RestTemplate 사용방법
    }

    public List<ItemDto> searchItems(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")  // 기본 url 지정
                .path("/v1/search/shop.json")
                .queryParam("display", 15)
                .queryParam("query", query)  // 쿼리형식  serch?query=(값)
                .encode()   // usL 인코딩
                .build()
                .toUri();   // URI 객체생성
        log.info("uri = " + uri);

        RequestEntity<Void> requestEntity = RequestEntity     // RequestEntity:HTTP 요청헤더와 URi 설정
                .get(uri)
                // 네이버 API를 사용하기 위해 X-Naver-Client-Id와 X-Naver-Client-Secret 헤더를 설정함
                .header("X-Naver-Client-Id", "G_qYr153_v75XpkfdQgD")
                .header("X-Naver-Client-Secret", "mHEOTFk0bJ")
                .build();

        // restTemplate.exchange를 사용하여 HTTP GET 요청을 보내고, 응답을 ResponseEntity<String>으로 받습니다
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.info("NAVER API Status Code : " + responseEntity.getStatusCode());

        return fromJSONtoItems(responseEntity.getBody());
    }

    // 받아온 JSON 형식의 응답 데이터를 파싱하여 ItemDto 객체로 변환하는 역할
    // JSON 데이터를 JSONObject로 변환하고, 그 중 "items" 필드의 값을 추출하여 ItemDto 리스트를 생성
    public List<ItemDto> fromJSONtoItems(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        JSONArray items  = jsonObject.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Object item : items) {
            ItemDto itemDto = new ItemDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}