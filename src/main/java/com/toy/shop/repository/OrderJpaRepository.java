package com.toy.shop.repository;

import com.toy.shop.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepository {

    private final EntityManager entityManager;

    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }

    public List<Order> findAll() {
        String jpql = "select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d";

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);

        return query.getResultList();
    }
}
