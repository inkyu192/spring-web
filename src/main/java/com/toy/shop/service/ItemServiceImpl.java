package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.domain.Item;
import com.toy.shop.exception.CommonException;
import com.toy.shop.repository.CategoryJpaRepository;
import com.toy.shop.repository.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.shop.common.ResultCode.CATEGORY_NOT_FOUND;
import static com.toy.shop.common.ResultCode.ITEM_NOT_FOUND;
import static com.toy.shop.dto.ItemDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemJpaRepository itemRepository;
//    private final ItemSpringJpaRepository itemRepository;

    private final CategoryJpaRepository categoryRepository;
//    private final CategorySpringJpaRepository categoryRepository;

    @Override
    @Transactional
    public Response save(SaveRequest requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new CommonException(CATEGORY_NOT_FOUND));

        Item item = Item.createItem(requestDto, category);

        itemRepository.save(item);

        return new Response(item);
    }

    @Override
    public List<Response> findAll(Long categoryId, String searchWord) {
        List<Item> items = itemRepository.findAll(categoryId, searchWord);

        return items.stream()
                .map(Response::new)
                .collect(Collectors.toList());
    }

    @Override
    public Response findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new CommonException(ITEM_NOT_FOUND));

        return new Response(item);
    }

    @Override
    @Transactional
    public Response update(Long id, UpdateRequest requestDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new CommonException(ITEM_NOT_FOUND));

        Category category = null;

        if (requestDto.getCategoryId() != null) {
            category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new CommonException(CATEGORY_NOT_FOUND));
        }

        item.updateItem(requestDto, category);

        return new Response(item);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new CommonException(ITEM_NOT_FOUND));

        itemRepository.delete(item);
    }
}
