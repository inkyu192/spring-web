package com.toy.shop.domain;

import com.toy.shop.controller.dto.BookSaveRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book extends BaseTimeDomain {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;
    private String description;
    private String author;
    private String publisher;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Book createBook(BookSaveRequestDto requestDto, Category category) {
        Book book = new Book();

        book.name = requestDto.getName();
        book.description = requestDto.getDescription();
        book.author = requestDto.getAuthor();
        book.publisher = requestDto.getPublisher();
        book.price = requestDto.getPrice();
        book.quantity = requestDto.getQuantity();
        book.category = category;

        return book;
    }
}
