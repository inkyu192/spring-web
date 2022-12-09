package com.toy.shop.repository;

import com.toy.shop.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookJpaRepository implements BookCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Book> findAll(Long categoryId, String searchWord) {
        String jpql = "select b from Book b";
        ArrayList<String> whereCondition = new ArrayList<>();

        if (categoryId != null) {
            whereCondition.add("b.categoryId = :categoryId");
        }

        if (StringUtils.hasText(searchWord)) {
            whereCondition.add("b.name like concat('%', :searchWord, '%') or b.author like concat('%', :searchWord, '%') or b.publisher like concat('%', :searchWord, '%')");
        }

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        if (StringUtils.hasText(searchWord)) {
            query.setParameter("searchWord", searchWord);
        }

        return query.getResultList();
    }
}
