package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }



    // ResponseDto가 Memo class와 같이 데이터베이스와 소통하는 클래스와 똑같은 형태가 대부분이다.
    // 같은 코드임에도 분리하는 이유는 데이터베이스와 소통하는 class는 조심스럽게 다뤄야하기 때문이다.
}