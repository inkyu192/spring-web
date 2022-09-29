package com.toy.shop.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private String desc;
    private String author;
    private String publisher;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
