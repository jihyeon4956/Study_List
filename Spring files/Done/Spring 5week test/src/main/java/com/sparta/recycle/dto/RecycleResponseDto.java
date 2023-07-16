package com.sparta.recycle.dto;

import com.sparta.recycle.entity.Recycle;
import lombok.Getter;

@Getter
public class RecycleResponseDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private String username;

    public RecycleResponseDto(Recycle recycle) {
        this.id = recycle.getId();
        this.title = recycle.getTitle();
        this.content = recycle.getContent();
        this.price = recycle.getPrice();
        this.username = recycle.getUsername();
    }
}
