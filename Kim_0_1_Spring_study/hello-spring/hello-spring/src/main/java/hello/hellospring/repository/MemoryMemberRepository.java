package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.*;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {

    // 저장용
    private static Map<Long, Member> store = new HashMap<>(); // 받아오는 값 적기
    private static long sequence = 0L;  // 시퀀스는 0,1,2... 값을 생성하는데 사용함

    @Override
    public Member save(Member member) {  //Member에 멤버객체를 필드값으로 진행하고 id에는 ++시퀀스를 값으로 넣는다.
        member.setId(++sequence);       //Map인 store에 키값을 시퀀스, 맴버객체를 값으로 가지는 HashMap을 만든다
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // null이여도 감쌀 수 있게 반환해줌
    }



    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 가장 처음꺼 찾는거
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}