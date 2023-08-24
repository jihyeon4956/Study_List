package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    // [생성자]
    public Memo(MemoRequestDto requestDto) {  // Controller에서 받아온 객체에 요소중 필요한것을 저장하여 Memo객체를 생성한다
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    // [메서드]
    public void update(MemoRequestDto requestDto) { // qkedkdhs requestDto 로 수정해준다.
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}