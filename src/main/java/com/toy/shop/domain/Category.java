package com.toy.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.toy.shop.dto.CategoryDto.SaveRequest;
import static com.toy.shop.dto.CategoryDto.UpdateRequest;

@Entity
@Getter
public class Category extends BaseDomain {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public static Category createCategory(SaveRequest requestDto) {
        Category category = new Category();
        category.name = requestDto.getName();
        category.description = requestDto.getDescription();

        return category;
    }

    public void updateCategory(UpdateRequest requestDto) {
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
    }
}
