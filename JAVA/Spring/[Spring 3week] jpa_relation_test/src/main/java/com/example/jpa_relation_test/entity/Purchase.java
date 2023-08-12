package com.example.jpa_relation_test.entity;

import javax.persistence.*;


@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;
}
