package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 이걸 해줘야 DI를 받아옴
public class BeanTest {
    // 기본적으로 @Autowired는 Bean 타입으로 찾는데 그게 중복일 경우 Bean 이름으로 찾는다

    // test1
//   @Autowired
//    Food pizza; // 직접지정
//
//    @Autowired
//    Food chicken;

    @Autowired
    @Qualifier("pizza")
    Food food;   // @으로 우선순위 지정

//    @Test
//    @DisplayName("테스트- Bean 이름을 지정했을 때")
//    void test1(){
//        pizza.eat();
//        chicken.eat();
//    }

    @Test
    @DisplayName("primary와 Qualifier중 우선순위 확인")
    void test2(){
        food.eat();
    }
}
