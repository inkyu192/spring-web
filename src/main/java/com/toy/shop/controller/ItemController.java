package com.toy.shop.controller;

import com.toy.shop.Entity.Item;
import com.toy.shop.controller.dto.ItemSaveRequest;
import com.toy.shop.controller.dto.ItemSaveResponse;
import com.toy.shop.controller.dto.ItemUpdateDto;
import com.toy.shop.controller.dto.ResponseDto;
import com.toy.shop.exception.UserException;
import com.toy.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseDto items() {
        log.info("상품 목록 조회 API 호출");

        throw new UserException("사용자 에러");
    }

    @PostMapping
    public ResponseDto addItem(@RequestBody @Valid ItemSaveRequest request) {
        log.info("상품 등록 API 호출");

        Item item = itemService.save(new Item(request.getName(), request.getPrice()));

        return new ResponseDto<>(new ItemSaveResponse(item));
    }

    @GetMapping("/{id}")
    public ResponseDto item(@PathVariable String id) {
        log.info("상품 상세 조회 API 호출");

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");

        return new ResponseDto("200", "정상응답", map);
    }

    @PatchMapping("{id}")
    public Object patchItem(@PathVariable String id, @RequestBody @Valid ItemUpdateDto dto) {
        log.info("상품 수정 API 호출");

        return dto;
    }

    @DeleteMapping("{id}")
    public Object deleteItem(@PathVariable String id) {
        return new Object();
    }
}
