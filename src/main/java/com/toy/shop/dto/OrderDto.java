package com.toy.shop.dto;

import com.toy.shop.domain.Order;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class OrderDto {

    @Getter
    public static class SaveRequest {

        @NotNull
        private Long memberId;

        @NotEmpty
        private String city;

        @NotEmpty
        private String street;

        @NotEmpty
        private String zipcode;

        @NotNull
        private List<OrderItemDto.SaveRequest> orderItems;
    }

    @Getter
    public static class Response {

        private Long id;

        public Response(Order order) {
            this.id = order.getId();
        }
    }
}
