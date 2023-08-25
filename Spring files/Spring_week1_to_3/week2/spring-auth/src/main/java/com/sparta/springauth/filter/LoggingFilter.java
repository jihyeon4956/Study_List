package com.sparta.springauth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "LoggingFilter")  // 로그 찍을때 "LoggingFilter" 이름으로 찍힘
@Component
@Order(1)
public class LoggingFilter implements Filter { // 필터 역할 수향하기 위해서 implements 함ㅡ dofliter메서드 오버라이딩
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  // 캐스팅
        String url = httpServletRequest.getRequestURI();    // URL 가져오기
        log.info(url);

        chain.doFilter(request, response); // 다음 Filter 로 이동
        // request에서 URL정보를 가져오는데 HttpServletRequest casting해서 타입을 변화남

        // 후처리
        log.info("비즈니스 로직 완료");
    }
}