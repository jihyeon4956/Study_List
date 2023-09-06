package com.sparta.junit5prac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AssertionTest {
Calculator calculator;

@BeforeEach
void setUp(){
    calculator = new Calculator();
}
    @Test
    @DisplayName("assertEquals")
    void test1() {
        Double result = calculator.operate(5, "/", 2);
        assertEquals(2.5, result);
    }

    @Test
    @DisplayName("assertEquals - Supplier")
    void test1_1() {
        Double result = calculator.operate(5, "/", 0);
        // 테스트 실패 시 메시지 출력 (new Supplier<String>())
        assertEquals(2.5, result, () -> "연산자 혹은 분모가 0이 아닌지 확인해보세요!");
    }

    @Test
    @DisplayName("assertNotEquals")
    void test1_2() {
        Double result = calculator.operate(5, "/", 0);
        assertNotEquals(2.5, result);
    }
}
