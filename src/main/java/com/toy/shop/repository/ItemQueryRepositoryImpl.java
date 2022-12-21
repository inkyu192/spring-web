package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Item;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toy.shop.domain.QItem.item;

@Repository
public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Item> findAll(Long categoryId, String searchWord) {
        return queryFactory.select(item)
                .from(item)
                .where(
                        categoryId(categoryId),
                        likeSearchWord(searchWord)
                )
                .fetch();
    }

    private BooleanExpression categoryId(Long categoryId) {
        if (categoryId != null) {
            return item.category.id.eq(categoryId);
        }
        return null;
    }

    private BooleanExpression likeSearchWord(String searchWord) {
        if (StringUtils.hasText(searchWord)) {
            return item.name.like("%" + searchWord + "%")
                    .or(item.description.like("%" + searchWord + "%"));
        }
        return null;
    }
}
