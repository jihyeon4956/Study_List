package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {
    // 현재 제어의 흐름이 Controller > Service > Repo로 직접생성임(new)

    private final MemoService memoService;

    public MemoController(MemoService memoService) {  // 만들어진 MemoServicd를 외부에서 파라미터로 전달 받아서 집어넣는다
        this.memoService = memoService;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // 서비스단 분리
        return memoService.createMemo(requestDto); // 클라이언트에서 전달받은 data를 사용하라고 같이 보내줌
    }

    // 입력한 키워드를 포함하는 게시글만 출력하기
    @GetMapping("memos/contents")   // Request Param 형식임 ( =?)
    public List<MemoResponseDto> getMemosByKeyword(String keyword){
        return memoService.getMemosByKeyword(keyword);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}