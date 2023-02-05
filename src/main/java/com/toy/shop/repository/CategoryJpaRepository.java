package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public long count() {
        return entityManager.createQuery("select count(c) from Category c", Long.class)
                .getSingleResult();
    }

    public List<Category> findAll() {
        return entityManager.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }

    public void delete(Category category) {
        entityManager.remove(category);
    }
}
