package com.toy.shop.controller;

import com.toy.shop.controller.dto.ItemSaveDto;
import com.toy.shop.controller.dto.ItemUpdateDto;
import com.toy.shop.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public Object items() {
        throw new UserException("사용자 에러");
    }

    @PostMapping
    public Object addItem(@RequestBody @Valid ItemSaveDto dto) {
        log.info("상품 등록 API 호출");

        log.info("상품 등록 API 정상 응답");

        return dto;
    }

    @GetMapping("/{id}")
    public Object item(@PathVariable String id) {
        return new Object();
    }

    @PatchMapping("{id}")
    public Object patchItem(@PathVariable String id, @RequestBody @Valid ItemUpdateDto dto) {
        log.info("상품 수정 API 호출");

        log.info("상품 수정 API 정상 응답");

        return dto;
    }

    @DeleteMapping("{id}")
    public Object deleteItem(@PathVariable String id) {
        return new Object();
    }
}
