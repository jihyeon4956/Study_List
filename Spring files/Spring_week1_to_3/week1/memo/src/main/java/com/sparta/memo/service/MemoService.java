package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 생성
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        Memo saveMemo = memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }

    // 일반조회
    public List<MemoResponseDto> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc()
                .stream()
                .map(MemoResponseDto::new)
                .toList();
    }

    // 키워드 포함된 게시글만 조회
    public List<MemoResponseDto> getMemosByKeyword(String keyword) {
        return memoRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword)
                .stream()
                .map(MemoResponseDto::new)
                .toList();
    }

    // 수정
    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
//        Memo memo = memoRepository.findById(id); 반환타입이 Optional이라 null체크를 반드시 해줘야한다
        Memo memo = findMemo(id);

        // memo 내용수정
        memo.update(requestDto);
        return id;
    }

    // 삭제
    public Long deleteMemo(Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        memoRepository.delete(memo);  // delete에는 지울 객체를 바로 넣어줌
        return id;

    }

    // 값 찾는 작업이 중복이라 메서드로 빼줌
    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
