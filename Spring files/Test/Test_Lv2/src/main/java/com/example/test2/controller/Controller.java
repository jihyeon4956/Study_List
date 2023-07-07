package com.example.test2.controller;

import com.example.test2.dto.PostRequestDto;
import com.example.test2.dto.PostResponseDto;
import com.example.test2.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final PostService postService;
    /**
     * 1. GET/ 전체 게시글 목록 조회 API
     * 2. POST/ 게시글 작성 API
     * 3. GET/ 선택한 게시글 조회 API
     * 4. PUT/ 선택한 게시글 수정 API
     * 5. DELETE/ 선택한 게시글 삭제 API
     */
x
    // 1.
    @GetMapping("/posts")
    public List<PostResponseDto> getPost() {
        return postService.getPost();
    }
    // 2.
    @PostMapping("/post")
    public PostResponseDto createPort(@RequestBody PostRequestDto postRequestDto


}
