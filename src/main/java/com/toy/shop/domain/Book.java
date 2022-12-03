package com.toy.shop.domain;

import com.toy.shop.dto.BookSaveRequestDto;
import com.toy.shop.dto.BookUpdateRequestDto;
import lombok.Getter;
import org.springframework.util.StringUtils;

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
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
        if (StringUtils.hasText(requestDto.getDescription())) this.description = requestDto.getDescription();
        if (StringUtils.hasText(requestDto.getAuthor())) this.author = requestDto.getAuthor();
        if (StringUtils.hasText(requestDto.getPublisher())) this.publisher = requestDto.getPublisher();
        if (requestDto.getPrice() != null) this.price = requestDto.getPrice();
        if (requestDto.getQuantity() != null) this.quantity = requestDto.getQuantity();
        if (category != null) this.category = category;
    }
}
