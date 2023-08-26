package com.sparta.springauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthApplication.class, args);
    }

}


    /**  <Bean> 수동등록은 언제 사용되나?
     * 기술적인 문제나 공통적인 관심사를 처리할 때 사용하는 객체들은 수동으로 등록하는 것이 좋다.
     * es) 공통 Log 처리나 같은 비니니스 Logic을 지원하기 위한 부가적이고 또 공통적인 기능들을 기술 지원 Bean이라고 부른다.
     * = 이러것들을 수동등록한다.
     * 비지니스 Logic Bean보다는 그 수가 적기 때문에 수동으로 등록하기 부담스럽지 않다.
     * 또한 수동으로 등록한 Bean은 문제가 생겼을 때 정확한 위치파악이 쉽다.
     * = 비밀범호 암호화빈
      */

