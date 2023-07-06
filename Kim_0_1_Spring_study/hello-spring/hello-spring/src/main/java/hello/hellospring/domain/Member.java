package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 식별자, 임의의 값으로 시스템 자동설정
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}