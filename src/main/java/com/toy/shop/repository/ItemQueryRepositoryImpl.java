package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Item;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toy.shop.domain.QCategory.category;
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
    public List<Item> findAll(Long categoryId, String name) {
        return queryFactory.select(item)
                .from(item)
                .join(category)
                .fetchJoin()
                .where(
                        categoryId(categoryId),
                        likeSearchWord(name)
                )
                .fetch();
    }

    private BooleanExpression categoryId(Long categoryId) {
        if (categoryId != null) {
            return category.id.eq(categoryId);
        }
        return null;
    }

    private BooleanExpression likeSearchWord(String name) {
        if (StringUtils.hasText(name)) {
            return item.name.like("%" + name + "%");
        }
        return null;
    }
}
