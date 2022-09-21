package com.toy.shop.controller.dto;

import com.toy.shop.Entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class ItemSaveResponse {

    private Long id;

    public ItemSaveResponse(Item item) {
        id = item.getId();
    }
}
