package com.toy.shopwebmvc.dto.request;

import com.toy.shopwebmvc.constant.Category;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ItemUpdateRequest(
        String name,
        String description,
        @Min(100)
        int price,
        @Max(value = 9999)
        int quantity,
        Category category
) {
}