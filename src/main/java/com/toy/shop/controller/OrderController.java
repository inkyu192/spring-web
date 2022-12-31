package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.shop.dto.OrderDto.Response;
import static com.toy.shop.dto.OrderDto.SaveRequest;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Object saveOrder(@RequestBody @Valid SaveRequest requestDto) {
        Response responseDto = orderService.save(requestDto);

        return new ResultDto<>(responseDto);
    }
}
