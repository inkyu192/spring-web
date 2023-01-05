package com.toy.shop.service;

import com.toy.shop.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto.Response save(ItemDto.SaveRequest requestDto);

    List<ItemDto.Response> findAll(Long categoryId, String name);

    ItemDto.Response findById(Long id);

    ItemDto.Response update(Long id, ItemDto.UpdateRequest requestDto);

    void delete(Long id);
}
