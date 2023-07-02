package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 스프링이 뜰 때 @Configuration를 읽고   @Bean 빈으로 등록하라는 명령어를 받아들임
    // memberService를  return new MemberService(); 이 로직을 호출해서 Spring Bean에 등록해줌
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
