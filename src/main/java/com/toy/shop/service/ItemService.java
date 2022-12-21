package com.toy.shop.service;

import com.toy.shop.dto.ItemResponseDto;
import com.toy.shop.dto.ItemSaveRequestDto;
import com.toy.shop.dto.ItemUpdateRequestDto;

import java.util.List;

public interface ItemService {

    ItemResponseDto save(ItemSaveRequestDto requestDto);

    List<ItemResponseDto> findAll(Long categoryId, String searchWord);

    ItemResponseDto findById(Long id);

    ItemResponseDto update(Long id, ItemUpdateRequestDto requestDto);

    void delete(Long id);
}
