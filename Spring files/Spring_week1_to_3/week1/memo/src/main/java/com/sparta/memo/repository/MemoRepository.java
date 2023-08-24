package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
// SimpleJpaRepository는 Spring Boot가 자동으로 생성해주는데 거기에 @Repository가 있어서 굳이 안적어줘도 됨
public interface MemoRepository extends JpaRepository<Memo, Long> {
                // JpaRepository<Entity 클래스, id 타입>
    //인터페이스의 구현 클래스를 직접 작성하지 않아도 JpaRepository 인터페이스를 통해 JPA의 기능을 사용할 수 있습니다.
}
