package com.toy.shop.repository;

import com.toy.shop.domain.Item;

import java.util.List;

public interface ItemQueryRepository {

    List<Item> findAll(Long categoryId, String searchWord);
}
