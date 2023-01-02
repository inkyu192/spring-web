package com.toy.shop.repository;

import com.toy.shop.domain.Category;
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
public class CategoryJpaRepository {

    private final EntityManager entityManager;

    public Category save(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
            return category;
        } else {
            return entityManager.merge(category);
        }
    }

    public List<Category> findAll() {
        String jpql = "select c from Category c";

        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        return query.getResultList();
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }

    public void delete(Category category) {
        entityManager.remove(category);
    }
}
