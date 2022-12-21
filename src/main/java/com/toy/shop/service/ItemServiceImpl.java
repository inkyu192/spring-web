package com.toy.shop.service;

import com.toy.shop.domain.Item;
import com.toy.shop.domain.Category;
import com.toy.shop.dto.ItemResponseDto;
import com.toy.shop.dto.ItemSaveRequestDto;
import com.toy.shop.dto.ItemUpdateRequestDto;
import com.toy.shop.exception.DataNotFoundException;
import com.toy.shop.repository.ItemJpaRepository;
import com.toy.shop.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.shop.common.ResultCode.ITEM_NOT_FOUND;
import static com.toy.shop.common.ResultCode.CATEGORY_NOT_FOUND;

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
    public ItemResponseDto save(ItemSaveRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new DataNotFoundException(CATEGORY_NOT_FOUND));

        Item item = Item.createItem(requestDto, category);

        itemRepository.save(item);

        return new ItemResponseDto(item);
    }

    @Override
    public List<ItemResponseDto> findAll(Long categoryId, String searchWord) {
        List<Item> items = itemRepository.findAll(categoryId, searchWord);

        return items.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ItemResponseDto findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));

        return new ItemResponseDto(item);
    }

    @Override
    @Transactional
    public ItemResponseDto update(Long id, ItemUpdateRequestDto requestDto) {
        Category category = null;
        Item item = itemRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));

        if (requestDto.getCategoryId() != null) {
            category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(() -> new DataNotFoundException(CATEGORY_NOT_FOUND));
        }

        item.updateItem(requestDto, category);

        return new ItemResponseDto(item);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));

        itemRepository.delete(item);
    }
}
