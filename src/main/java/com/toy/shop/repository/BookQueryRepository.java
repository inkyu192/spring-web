package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Book;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toy.shop.domain.QBook.book;

@Repository
public class BookQueryRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public BookQueryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Book> findAll(Long categoryId, String searchWord) {
        return queryFactory.select(book)
                .from(book)
                .where(
                        categoryId(categoryId),
                        likeSearchWord(searchWord)
                )
                .fetch();
    }

    private BooleanExpression categoryId(Long categoryId) {
        if (categoryId != null) {
            return book.category.id.eq(categoryId);
        }
        return null;
    }

    private BooleanExpression likeSearchWord(String searchWord) {
        if (StringUtils.hasText(searchWord)) {
            return book.name.like("%" + searchWord + "%")
                    .or(book.description.like("%" + searchWord + "%"))
                    .or(book.author.like("%" + searchWord + "%"))
                    .or(book.publisher.like("%" + searchWord + "%"));
        }

        return null;
    }
}
