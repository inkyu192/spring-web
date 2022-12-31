package com.toy.shop.service;

import static com.toy.shop.dto.OrderDto.Response;
import static com.toy.shop.dto.OrderDto.SaveRequest;

public interface OrderService {

    Response save(SaveRequest requestDto);
}
