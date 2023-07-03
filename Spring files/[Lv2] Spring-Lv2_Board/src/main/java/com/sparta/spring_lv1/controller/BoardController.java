package com.sparta.spring_lv1.controller;


import com.sparta.spring_lv1.dto.BoardRequestDto;
import com.sparta.spring_lv1.dto.BoardResponseDto;
import com.sparta.spring_lv1.entity.Board;
import com.sparta.spring_lv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    // 데이터베이스
    private final BoardService boardService; // @RequiredArgsConstructor



    // 게시글 생성하기
    @PostMapping("/boards")
    public BoardResponseDto creatBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);  //
    }

    // 게시글 전체조회
    @GetMapping("/boards")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();  //
    }

    // 게시글 선택조회
    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);  //
    }

    // 게시글 수정하기 - 비밀번호 검증기능 필요함
    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);  //
    // 업데이트 할 게시글의 {id}를 받아옴, 수정된 정보를 JSON으로 받아옴

    }

    // 메모 삭제하기
    @DeleteMapping("/boards/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteMemo(id, requestDto);  //
    }
}
