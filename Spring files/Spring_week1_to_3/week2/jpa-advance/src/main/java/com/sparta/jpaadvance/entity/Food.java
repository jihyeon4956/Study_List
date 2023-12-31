package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToOne   //  FetchType.EAGER;
//    @ManyToOne(fetch = FetchType.LAZY)  직접 변경
    @JoinColumn(name = "user_id")
    private User user;

}