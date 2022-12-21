package com.toy.shop.domain;

import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category extends BaseDomain {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public static Category createCategory(CategorySaveRequestDto requestDto) {
        Category category = new Category();
        category.name = requestDto.getName();

        return category;
    }

    public void updateCategory(CategoryUpdateRequestDto requestDto) {
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
    }
}
