package com.toy.shop.dto;

import com.toy.shop.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Long categoryId;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.categoryId = item.getCategory().getId();
    }
}
