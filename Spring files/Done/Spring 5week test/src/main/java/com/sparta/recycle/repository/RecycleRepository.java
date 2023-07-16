package com.sparta.recycle.repository;

import com.sparta.recycle.entity.Recycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycleRepository extends JpaRepository<Recycle, Long> {
    List<Recycle> findAll();
}
