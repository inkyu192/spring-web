package com.toy.shop;

import com.toy.shop.controller.dto.CategorySaveRequestDto;
import com.toy.shop.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.categoryInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void categoryInit() {
            for (int i = 1; i <= 10; i++) {
                CategorySaveRequestDto requestDto = new CategorySaveRequestDto();
                requestDto.setName("카테고리_" + i);

                em.persist(Category.createCategory(requestDto));
            }
        }
    }
}
