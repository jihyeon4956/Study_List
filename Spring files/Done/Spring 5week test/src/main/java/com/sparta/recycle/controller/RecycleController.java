package com.sparta.recycle.controller;


import com.sparta.recycle.dto.RecycleRequestDto;
import com.sparta.recycle.dto.RecycleResponseDto;
import com.sparta.recycle.service.RecycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecycleController {
    private final RecycleService recycleService;

    // 게시글 생성하기
    @PostMapping("/post")
    public RecycleResponseDto createPost(@RequestBody RecycleRequestDto requestDto) {
        return recycleService.createPost(requestDto);  //
    }

    // 게시글 전체조회
    @GetMapping("/post")
    public List<RecycleResponseDto> getPost() {
        return recycleService.getPost();  //
    }

    // 게시글 상세조회
    @GetMapping("/post/{id}")
    public RecycleResponseDto getPostById(@PathVariable Long id) {
        return recycleService.getPostById(id);  //
    }

    @PutMapping("/post/{id}")
    public RecycleResponseDto updatePost(@PathVariable Long id, @RequestBody RecycleRequestDto requestDto) {
        return recycleService.updatePost(id, requestDto);  //
    }

    // 메모 삭제하기
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody RecycleRequestDto requestDto) {
        return recycleService.deletePost(id, requestDto);  //
    }
}
