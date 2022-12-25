package com.toy.shop.service;

import com.toy.shop.dto.OrderSaveRequestDto;

public interface OrderService {

    Long save(OrderSaveRequestDto requestDto);
}
