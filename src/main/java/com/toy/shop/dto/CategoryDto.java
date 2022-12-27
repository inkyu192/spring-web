package com.toy.shop.dto;

import com.toy.shop.domain.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

public class CategoryDto {

    @Getter
    public static class SaveRequest {

        @NotEmpty
        private String name;

        @NotEmpty
        private String description;
    }

    @Getter
    public static class UpdateRequest {

        private String name;

        private String description;
    }

    @Getter
    public static class Response {

        private Long id;
        private String name;

        private String description;

        private List<ItemDto.Response> items;

        public Response(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
            items = category.getItems().stream()
                    .map(ItemDto.Response::new)
                    .toList();
        }
    }
}
