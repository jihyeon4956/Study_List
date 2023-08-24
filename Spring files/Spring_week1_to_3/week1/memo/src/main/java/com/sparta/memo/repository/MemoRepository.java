package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
// SimpleJpaRepository는 Spring Boot가 자동으로 생성해주는데 거기에 @Repository가 있어서 굳이 안적어줘도 됨
// JpaRepository<Entity 클래스, id 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {
     // Spring Data JPA에서는 메서드 이름으로 SQL을 생성할 수 있는 Query Methods 기능을 제공한다.
    // JpaRepository interface에서 해당 interface와 맵핑되어 있는 테이블(지금은 Memo)에
    // 요청하고자 하는 SQL을 method 이름을 사용해서 선언할 수 있음


        List<Memo> findAllByOrderByModifiedAtDesc();
        // ModifiedAt이라는 필드 데이터를 기준으로 내림차순 정렬해서 전체 데이터를 내보낼거다

        List<Memo> findAllByUsername(String username);
        // username이 쓴 글만 모두 가져오기
        // 파라미터로 username을 넣어줘야함

}
