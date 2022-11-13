package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import com.toy.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResultDto category(@PathVariable Long id) {
        CategoryResponseDto responseDto = categoryService.findById(id);

        return new ResultDto(responseDto);
    }

    @GetMapping
    public ResultDto categories() {
        List<CategoryResponseDto> list = categoryService.findAll();

        return new ResultDto(list);
    }

    @PatchMapping("{id}")
    public ResultDto patchCategory(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.update(id, requestDto);

        return new ResultDto(responseDto);
    }

    @DeleteMapping("{id}")
    public ResultDto deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);

        return new ResultDto(null);
    }
}
