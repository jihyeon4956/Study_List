package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();


    @PostMapping("/memos") // @RequestBody =-> 데이터는 Body 부분에 JSON 형태로 넘어올거라서 애노테이션 쓰기
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entity로 저장하기 (데이터베이스와 소통하는 entity class로 변경해주기)
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장 ( = 확인한 id값과 받아온 memo값)
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
//        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
//        return memoResponseDto;

        return (new MemoResponseDto(memo));  // 중복제거
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map To List
//
//        List<MemoResponseDto> responseList = memoList.values().stream()
//                .map(MemoResponseDto::new).toList();
//
//        return responseList;
//
        return memoList.values().stream()
                .map(MemoResponseDto::new).toList();
    }
}
