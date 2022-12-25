package com.toy.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSaveRequestDto {

    @NotEmpty
    private Long memberId;

    @NotEmpty
    private Long itemId;

    @NotNull
    @Min(0)
    private int count;
}
