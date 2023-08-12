package com.example.jpa_relation_test.repository;

import com.example.jpa_relation_test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookStoreId(Long bookStoreId);
}
