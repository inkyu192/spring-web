package com.toy.shop.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Category;
import com.toy.shop.domain.QCategory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.shop.domain.QCategory.*;

@Repository
public class CategoryQueryRepository {

    private final JPAQueryFactory query;

    public CategoryQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Category> findAll(String searchWord) {
        return query.select(category)
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
