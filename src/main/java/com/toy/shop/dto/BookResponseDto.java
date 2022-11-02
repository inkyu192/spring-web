package com.toy.shop.dto;

import com.toy.shop.domain.Book;
import com.toy.shop.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BookResponseDto {

    private Long id;
    private String name;
    private String description;
    private String author;
    private String publisher;
    private Integer price;
    private Integer quantity;
    private Long categoryId;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.price = book.getPrice();
        this.quantity = book.getQuantity();
        this.categoryId = book.getCategory().getId();
    }
}
