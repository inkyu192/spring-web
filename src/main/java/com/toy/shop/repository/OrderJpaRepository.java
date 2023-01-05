package com.toy.shop.repository;

import com.toy.shop.domain.DeliveryStatus;
import com.toy.shop.domain.Order;
import com.toy.shop.domain.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<Order> findAll(Long memberId, OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        String jpql = "select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d";

        ArrayList<String> whereCondition = new ArrayList<>();

        if (memberId != null) whereCondition.add("m.id = :memberId");
        if (orderStatus != null) whereCondition.add("o.status = :orderStatus");
        if (deliveryStatus != null) whereCondition.add("o.status = :deliveryStatus");

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);

        if (memberId != null) query.setParameter("memberId", memberId);
        if (orderStatus != null) query.setParameter("orderStatus", orderStatus);
        if (deliveryStatus != null) query.setParameter("deliveryStatus", deliveryStatus);

        return query.getResultList();
    }
}
