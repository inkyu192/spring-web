package com.toy.shop.controller;

import com.toy.shop.controller.dto.ItemSaveDto;
import com.toy.shop.controller.dto.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public Object items() {
        return new Object();
    }

    @PostMapping
    public Object addItem(@RequestBody @Valid ItemSaveDto dto, BindingResult bindingResult) {
        log.info("상품 등록 API 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors: {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("상품 등록 API 정상 응답");

        return dto;
    }

    @GetMapping("/{id}")
    public Object item(@PathVariable String id) {
        return new Object();
    }

    @PatchMapping("{id}")
    public Object patchItem(@PathVariable String id, @RequestBody @Valid ItemUpdateDto dto, BindingResult bindingResult) {
        log.info("상품 수정 API 호출");

        if (dto.getPrice() != null && dto.getQuantity() != null) {
            int resultPrice = dto.getPrice() * dto.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors: {}", bindingResult);
            return bindingResult.getAllErrors();
        }

        return dto;
    }

    @DeleteMapping("{id}")
    public Object deleteItem(@PathVariable String id) {
        return new Object();
    }
}
