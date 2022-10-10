package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.controller.dto.CategoryResponseDto;
import com.toy.shop.controller.dto.CategorySaveRequestDto;
import com.toy.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResultDto addCategory(@RequestBody @Valid CategorySaveRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.save(requestDto);

        return new ResultDto(responseDto);
    }
}
