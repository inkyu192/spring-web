package com.toy.shop.repository;

import com.toy.shop.domain.Item;
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
public class ItemJpaRepository {

    private final EntityManager entityManager;

    public Item save(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return item;
        } else {
            return entityManager.merge(item);
        }
    }

    public List<Item> findAll(Long categoryId, String searchWord) {
        String jpql = "select b from Item b";
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

        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        if (StringUtils.hasText(searchWord)) {
            query.setParameter("searchWord", searchWord);
        }

        return query.getResultList();
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    public void delete(Item item) {
        entityManager.remove(item);
    }
}
