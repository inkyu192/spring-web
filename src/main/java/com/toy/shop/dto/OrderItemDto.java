package com.toy.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class OrderItemDto {

    @Getter
    public static class SaveRequest {

        @NotEmpty
        private Long itemId;

        @NotNull
        private int count;
    }
}
