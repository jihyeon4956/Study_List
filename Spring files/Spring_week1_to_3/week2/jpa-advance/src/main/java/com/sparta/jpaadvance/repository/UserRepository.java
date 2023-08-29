package com.sparta.jpaadvance.repository;

import com.sparta.jpaadvance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);  // 동적처리 하려면 파라미터로 줘야함
}
