package com.toy.shop.service;

import com.toy.shop.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto.Response saveItem(ItemDto.SaveRequest requestDto);

    List<ItemDto.Response> items(Long categoryId, String name);

    ItemDto.Response item(Long id);

    ItemDto.Response updateItem(Long id, ItemDto.UpdateRequest requestDto);

    void deleteItem(Long id);
}
