package com.toy.shop.domain;

import com.toy.shop.dto.BookSaveRequestDto;
import com.toy.shop.dto.BookUpdateRequestDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

    public void updateBook(BookUpdateRequestDto requestDto, Category category) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.author = requestDto.getAuthor();
        this.publisher = requestDto.getPublisher();
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
        this.category = category;
    }
}
