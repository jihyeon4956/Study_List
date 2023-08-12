package com.example.jpa_relation_test.service;

import com.example.jpa_relation_test.entity.Book;
import com.example.jpa_relation_test.entity.BookStore;
import com.example.jpa_relation_test.entity.Member;
import com.example.jpa_relation_test.repository.BookRepository;
import com.example.jpa_relation_test.repository.BookStoreRepository;
import com.example.jpa_relation_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookStoreRepository bookStoreRepository;

    @Transactional
    public void signup(Member member) {
        memberRepository.save(member);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }



    @Transactional
    public void updateBook(Book book, Long bookStoreId, Long bookId) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookStore bookStore = bookStoreRepository.findById(bookStoreId)
                .orElseThrow(() -> new IllegalArgumentException("BookStore not found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setStock(book.getStock()); // setStock만 진행하면 되는게 아닌지?
        existingBook.setBookStore(bookStore);

        bookRepository.save(existingBook);
    }

    //////////////////////////////////////////////





    @Transactional
    public List<Book> findBook(Long bookStoreId) {
        return bookRepository.findByBookStoreId(bookStoreId);
    }

    @Transactional    // POST http://localhost:8080/bookstore/2/book/1
    public void transferBook(Long bookId, Long bookStoreId) { // bookStoreId : name, address
        Book book = bookRepository.findById(bookId)
                .orElseThrow();
        BookStore bookStore = bookStoreRepository.findById(bookStoreId)
                .orElseThrow();

        book.chageBookStore(bookStore);
    }
}
