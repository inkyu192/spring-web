package com.toy.shop.service;

import java.util.List;

import static com.toy.shop.dto.ItemDto.*;

public interface ItemService {

    Response save(SaveRequest requestDto);

    List<Response> findAll(Long categoryId, String searchWord);

    Response findById(Long id);

    Response update(Long id, UpdateRequest requestDto);

    void delete(Long id);
}
