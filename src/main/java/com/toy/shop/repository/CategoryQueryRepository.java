package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.shop.domain.QCategory.*;

@Repository
public class CategoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CategoryQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Category> findAll(String searchWord) {
        return queryFactory.select(category)
                .from(category)
                .where(likeSearchWord(searchWord))
                .fetch();
    }

    private BooleanExpression likeSearchWord(String searchWord) {
        if (StringUtils.hasText(searchWord)) {
            return category.name.like("%" + searchWord + "%");
        }

        return null;
    }
}
