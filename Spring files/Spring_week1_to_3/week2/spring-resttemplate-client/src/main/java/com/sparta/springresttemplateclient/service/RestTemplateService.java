package com.sparta.springresttemplateclient.service;

import com.sparta.springresttemplateclient.dto.ItemDto;
import com.sparta.springresttemplateclient.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;  // RestTemplate 사용하려면 이거 추가하고

    public RestTemplateService(RestTemplateBuilder builder) { // RestTemplateBuilder 가져와서 .build() 해줘야함
        this.restTemplate = builder.build();
    }


    public ItemDto getCallObject(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")  // 서버 입장의 서버에 보낼준비
                .path("/api/server/get-call-obj")  // 보내는 주소
                .queryParam("query", query)  // query형식(=?), Mac, IPad 등을 입력함(동적으로 URI생성)
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(uri, ItemDto.class);
        // getForEntity: 해당 URI를 GET방식으로 서버에 요청을 진행하는것
        // ItemDto.class: 두번째 파라미터에 변화하고싶은 클래스 형태를 주면 그 형태로 들어옴

        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }


    public List<ItemDto> getCallList() {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/get-call-list")
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        log.info("statusCode = " + responseEntity.getStatusCode());
        log.info("Body = " + responseEntity.getBody());

        return fromJSONtoItems(responseEntity.getBody());
    }

    public ItemDto postCall(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/post-call/{query}")  // @PathVariable 방식
                .encode()
                .build()
                .expand(query)
                .toUri();
        log.info("uri = " + uri);

        User user = new User("Robbie", "1234");

        ResponseEntity<ItemDto> responseEntity = restTemplate.postForEntity(uri, user, ItemDto.class);
        // postForEntity(uri, HttpBody에 넣어줄 테이더(user), 변환형태(ItemDto.class));

        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    // Body가 아니라 HTTP header에 데이터를 담는 방법
    public List<ItemDto> exchangeCall(String token) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:7070")
                .path("/api/server/exchange-call")
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        User user = new User("Robbie", "1234");

        RequestEntity<User> requestEntity = RequestEntity
                .post(uri)
                .header("X-Authorization", token)
                .body(user);  // user 데이터 넣기

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        // exchange(requestEntity, 받아올 데이터 타입(String.class));

        return fromJSONtoItems(responseEntity.getBody());
    }

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