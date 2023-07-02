package com.sparta.entity;

import jakarta.persistence.*;


@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
        // 이 클래스가 JPA가 관리하는 클래스라는걸 의미함
        // @Table(name = "memo")으로 이름을 지정할 수도 있으나 디폴드가 클래스이름임.
        // JPA는 Entity클래스를 인스턴스화 할 때 기본생성자를 사용하기 때문에 반드시 현재 Entity 클래스에서 기본생성자가 있어야 한다.
@Table(name = "memo") // 매핑할 테이블의 이름을 지정
                      // 마찬가지로 기본값은 클래스명이나 명확하게 지정하는게 좋음
public class Memo {
    @Id         // 테이블의 기본키(primary key), 기본키가 없으면 오류남
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment 설정함, 자동으로 ++// 테스트용으로 잠시 주석
    private Long id;

    // nullable: null 허용 여부, 디폴트는 true
    // unique: 중복 허용 여부 (false 일때 중복 허용), 디폴트는 false
    @Column(name = "username", nullable = false, unique = true) // @Column: 필드와 매핑할 컬럼의 이름을 지정한다
    private String username;         // Database의  "username"이라는 컬럼과  String username을 매핑하겠다는 의미임
                                     // 디폴트는 필드명임

    // length: 컬럼 길이 지정, 디폴트는 255
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;


    // Setter 사용시 주의 : 이 클래스는 데이버가 생성된 상태이기 때문에 entity클래스는 조심스럽게 다뤄야 한다.(데이터베이스와 매핑되기 때문)
    // Setter는 고민 후 필요한 곳에만 달아준다
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}