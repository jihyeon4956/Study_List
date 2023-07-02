package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);  // 회원을 저장하면 저장된 회원이 반환된다
    Optional<Member> findById(Long id);  // 임의로 부여된 ID로 회원 찾기
    Optional<Member> findByName(String name);
    List<Member> findAll();     // 모든 회원 리스트를 반환한다

    // Optional : null일 경우 감싸서 반환하는것
}
