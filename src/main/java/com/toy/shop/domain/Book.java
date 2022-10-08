package com.toy.shop.domain;

import com.toy.shop.controller.dto.BookSaveRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    public static Book createBook(BookSaveRequestDto requestDto) {
        Book book = new Book();
        book.name = requestDto.getName();
        book.price = requestDto.getPrice();

        return book;
    }
}
