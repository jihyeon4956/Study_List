package com.sparta.springresttemplateclient.dto;

import lombok.Getter;

@Getter
public class Item {
    private String title;
    private int price;

    public Item(String title, int price) {
        this.title = title;
        this.price = price;
    }
}