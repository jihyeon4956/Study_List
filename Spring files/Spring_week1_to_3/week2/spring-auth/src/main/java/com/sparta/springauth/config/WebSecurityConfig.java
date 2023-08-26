package com.sparta.springauth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함(꼭 추가해야됨)
public class WebSecurityConfig {
    // Spring Security를 사용해서 인증, 인가를 편리하게 해줄 수 있다.

    @Bean // 우리가 Spring Security를 사용할 때 URL에 따라 인가유무를 설정해줘야하고 어떤 기능을 사용하고 안하는지 설정해야함
    // 그런 작업을 securityFilterChain()에서 수행해준다.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HttpSecurity에 http객체를 사용해서 우리가 필요한, 원하는 설정들을 전부 한 다음 .build를 하면서 return해준다(그 객체가 Bean으로 등록됨)

        // CSRF 설정
        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        // 즉, resources에 접근하는건 따로 인증처리하지 않아도 전부 허용하겠다는 의미
                        .requestMatchers("/api/user/**").permitAll()
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        // 로그인 사용
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }
}