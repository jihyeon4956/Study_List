package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {


    // 회원서비스를 만들기 위한 repository 생성
    private final MemberRepository memberRepository;

    @Autowired  // 이걸 붙이면 MemberRepository에서 가져와서 주입해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
// memberRepository 를 변수로 선언 후 외부에서 만들어줌 (외부에서 넣어줌 -> DI)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);  // 중복회원 검증
        memberRepository.save(member);
        return member.getId();   // 회원가입 하면 ID를 반환해주기로 함
    }

    private void validateDuplicateMember(Member member) {  // 기능구현은 메서드로 만드는게 낫다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 선택회원 조회
     */

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
