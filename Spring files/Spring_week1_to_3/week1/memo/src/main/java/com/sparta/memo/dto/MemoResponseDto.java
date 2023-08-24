package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }

    public MemoResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;
    }

    // ResponseDto가 Memo class와 같이 데이터베이스와 소통하는 클래스와 똑같은 형태가 대부분이다.
    // 같은 코드임에도 분리하는 이유는 데이터베이스와 소통하는 class는 조심스럽게 다뤄야하기 때문이다.
}