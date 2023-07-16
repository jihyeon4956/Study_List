package com.sparta.recycle.service;


import com.sparta.recycle.dto.RecycleRequestDto;
import com.sparta.recycle.dto.RecycleResponseDto;
import com.sparta.recycle.entity.Recycle;
import com.sparta.recycle.repository.RecycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecycleService {

    private final RecycleRepository recycleRepository;

    // 생성
    public RecycleResponseDto createPost(RecycleRequestDto requestDto) {
        Recycle recycle = new Recycle(requestDto);
        Recycle saveRecycle = recycleRepository.save(recycle);
        RecycleResponseDto recycleResponseDto = new RecycleResponseDto(saveRecycle);

        return recycleResponseDto;
    }


    // 전체조회
    public List<RecycleResponseDto> getPost() {

        return recycleRepository.findAll().stream().map(RecycleResponseDto::new).toList();
    }


    // 선택조회
    public RecycleResponseDto getPostById(Long id) {
        Recycle recycle = recycleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));

        return new RecycleResponseDto(recycle);
    }

    // 수정
    @Transactional
    public RecycleResponseDto updatePost(Long id, RecycleRequestDto requestDto) {
        Recycle recycle = findPost(id);
        if (recycle == null){
            throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }
        recycle.update(requestDto);
        return new RecycleResponseDto(recycle);
    }

    // 삭제
    public String deletePost(Long id, RecycleRequestDto requestDto) {

        Recycle recycle = findPost(id);
        recycleRepository.delete(recycle);
        return "삭제완료";
    }

    private Recycle findPost(Long id) {
        return recycleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
