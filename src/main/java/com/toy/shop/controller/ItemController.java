package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.ItemDto;
import com.toy.shop.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Object saveItem(@RequestBody @Valid ItemDto.SaveRequest requestDto) {
        ItemDto.Response responseDto = itemService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object items(@RequestParam(required = false) Long categoryId,
                        @RequestParam(required = false) String name) {
        List<ItemDto.Response> list = itemService.findAll(categoryId, name);

        return new ResultDto<>(list);
    }

    @GetMapping("/{id}")
    public Object item(@PathVariable Long id) {
        ItemDto.Response responseDto = itemService.findById(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("/{id}")
    public Object updateItem(@PathVariable Long id,
                             @RequestBody @Valid ItemDto.UpdateRequest requestDto) {
        ItemDto.Response responseDto = itemService.update(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("/{id}")
    public Object deleteItem(@PathVariable Long id) {
        itemService.delete(id);

        return new ResultDto<>();
    }
}
