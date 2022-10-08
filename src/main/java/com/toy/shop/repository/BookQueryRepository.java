package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.shop.domain.QBook.book;

@Repository
public class BookQueryRepository {

    private final JPAQueryFactory query;

    public BookQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Book> findAll(Long categoryId, String searchWord) {
        return query.select(book)
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
            return book.name.like("%" + searchWord + "%");
        }
        return null;
    }

}
