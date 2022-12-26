package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.toy.shop.dto.CategoryDto.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Object saveCategory(@RequestBody @Valid SaveRequest requestDto) {
        Response responseDto = categoryService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object categories(@RequestParam(required = false) String searchWord) {
        List<Response> list = categoryService.findAll(searchWord);

        return new ResultDto<>(list);
    }

    @GetMapping("/{id}")
    public Object category(@PathVariable Long id) {
        Response responseDto = categoryService.findById(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateCategory(@PathVariable Long id, @RequestBody @Valid UpdateRequest requestDto) {
        Response responseDto = categoryService.update(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("{id}")
    public Object deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);

        return new ResultDto<>();
    }
}
