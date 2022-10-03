package com.toy.shop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseDomain {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;
    private String desc;
    private String author;
    private String publisher;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
