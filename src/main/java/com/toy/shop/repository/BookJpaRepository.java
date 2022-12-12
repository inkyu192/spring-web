package com.toy.shop.repository;

import com.toy.shop.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookJpaRepository {

    private final EntityManager entityManager;

    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

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

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public void delete(Book book) {
        entityManager.remove(book);
    }
}
