package com.toy.shop.domain;

import com.toy.shop.dto.ItemSaveRequestDto;
import com.toy.shop.dto.ItemUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Entity
@Getter
public class Item extends BaseDomain {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private String description;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Item createItem(ItemSaveRequestDto requestDto, Category category) {
        Item item = new Item();

        item.name = requestDto.getName();
        item.description = requestDto.getDescription();
        item.price = requestDto.getPrice();
        item.quantity = requestDto.getQuantity();
        item.category = category;

        return item;
    }

    public void updateItem(ItemUpdateRequestDto requestDto, Category category) {
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
        if (StringUtils.hasText(requestDto.getDescription())) this.description = requestDto.getDescription();
        if (requestDto.getPrice() != null) this.price = requestDto.getPrice();
        if (requestDto.getQuantity() != null) this.quantity = requestDto.getQuantity();
        if (category != null) this.category = category;
    }
}
