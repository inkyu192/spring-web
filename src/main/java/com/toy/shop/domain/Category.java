package com.toy.shop.domain;

import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public static Category createCategory(CategorySaveRequestDto requestDto) {
        Category category = new Category();
        category.name = requestDto.getName();

        return category;
    }

    public void updateCategory(CategoryUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
    }
}
