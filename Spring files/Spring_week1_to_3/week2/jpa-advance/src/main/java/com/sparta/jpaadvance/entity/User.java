package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // User Entity만 Save하면 foodList에 들어있는 Food Entity도 저장되게 만들고싶음 -> 영속성 전이에 persist
    // 영속성 전의를 사용해서 저장하려고 하는 연관된 Entity의 애노테이션에  cascade = CascadeType.PERSIST를 해주면 된다
//    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)  // 영속성 전이 설정함
//    @OneToMany(mappedBy = "user")
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setUser(this);
    }
}