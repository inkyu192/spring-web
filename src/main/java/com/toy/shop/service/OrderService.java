package com.toy.shop.service;

import com.toy.shop.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto.Response save(OrderDto.SaveRequest requestDto);

    List<OrderDto.Response> findAll();
}
