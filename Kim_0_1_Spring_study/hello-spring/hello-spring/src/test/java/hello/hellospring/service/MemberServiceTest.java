package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        // when
        Member member2 = new Member();
        member2.setName("spring");
        // then
        memberService.join(member1);
        try{
            memberService.join(member1);
            fail("d");
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");  //
        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}