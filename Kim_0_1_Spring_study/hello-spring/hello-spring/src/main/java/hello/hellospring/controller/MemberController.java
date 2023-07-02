package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// MemberController는 MemberService를 통해서 회원가입을 하고 MemberController를 통해서 회원조회를 할 수 있어야 한다
// => MemberController가 MemberController를 의존한다
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
