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

    public List<Item> findAll(Long categoryId, String name) {
        String jpql = "select i from Item i" +
                " join fetch i.category c";

        ArrayList<String> whereCondition = new ArrayList<>();

        if (categoryId != null) whereCondition.add("c.id = :categoryId");
        if (StringUtils.hasText(name)) whereCondition.add("i.name like concat('%', :name, '%')");

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);

        if (categoryId != null) query.setParameter("categoryId", categoryId);
        if (StringUtils.hasText(name)) query.setParameter("name", name);

        return query.getResultList();
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    public void delete(Item item) {
        entityManager.remove(item);
    }
}
