package com.toy.shop.business.item.controller;

import com.toy.shop.common.ApiResponseDto;
import com.toy.shop.business.item.dto.ItemDto;
import com.toy.shop.business.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Object saveItem(@RequestBody @Valid ItemDto.Save requestDto) {
        ItemDto.Response responseDto = itemService.saveItem(requestDto);

        return new ApiResponseDto<>(responseDto);
    }

    @GetMapping
    public Object items(@RequestParam(required = false) Long categoryId,
                        @RequestParam(required = false) String name,
                        Pageable pageable) {
        Page<ItemDto.Response> page = itemService.items(categoryId, name, pageable);

        return new ApiResponseDto<>(page);
    }

    @GetMapping("/{id}")
    public Object item(@PathVariable Long id) {
        ItemDto.Response responseDto = itemService.item(id);

        return new ApiResponseDto<>(responseDto);
    }

    @PatchMapping("/{id}")
    public Object updateItem(@PathVariable Long id,
                             @RequestBody @Valid ItemDto.Update requestDto) {
        ItemDto.Response responseDto = itemService.updateItem(id, requestDto);

        return new ApiResponseDto<>(responseDto);
    }

    @DeleteMapping("/{id}")
    public Object deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);

        return new ApiResponseDto<>();
    }
}