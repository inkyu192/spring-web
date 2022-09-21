package com.toy.shop.service;

import com.toy.shop.Entity.Item;
import com.toy.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

//    private final ItemQueryRepository itemQueryRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
