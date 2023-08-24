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

    // 생성
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

    // 조회
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

    // 수정
    @PutMapping("/memos/{id}")  // 반환은 업데이트된 id값만 줄거임
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // 데이터를 수정한다는것은 어떤 데이터에 대한 내용(contents와 username등)이 같이 넘어옴
        // 클라이언트에서 Body부분에 넘어올거니까 @RequestBody로 받아줌, 넘어오는건 클라이언트에서 입력한 수정값임!!
        // 넘어온 데이터를 받는건 RequestDto

        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {   // containsKey: Map의 메소드로  key에 받아온(id)값이 존재하는지 포함여부로 확인
            // 해당 메모 가져오기
            Memo memo = memoList.get(id);  // id에 해당하는 value를 (= Memo객체) 반환한다

            // memo 수정
            memo.update(requestDto);
            return memo.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 삭제
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {
            // 해당 메모 삭제하기
            memoList.remove(id);   // Map 내장메서드임
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

    }
}
