package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryJpaRepository implements CategoryCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Category> findAll(String searchWord) {
        String jpql = "select c from Category c";
        ArrayList<String> whereCondition = new ArrayList<>();

        if (StringUtils.hasText(searchWord)) {
            whereCondition.add("c.name like concat('%', :searchWord, '%')");
        }

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        if (StringUtils.hasText(searchWord)) {
            query.setParameter("searchWord", searchWord);
        }

        return query.getResultList();
    }
}