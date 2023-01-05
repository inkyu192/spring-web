package com.toy.shop.repository;

import com.toy.shop.domain.Order;
import com.toy.shop.domain.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    public List<Order> findAll(String memberName, OrderStatus orderStatus) {
        String jpql = "select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d";

        ArrayList<String> whereCondition = new ArrayList<>();

        if (StringUtils.hasText(memberName)) whereCondition.add("m.name like concat('%', :memberName, '%')");
        if (orderStatus != null) whereCondition.add("o.status = :status");

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);

        if (StringUtils.hasText(memberName)) query.setParameter("memberName", memberName);
        if (orderStatus != null) query.setParameter("status", orderStatus);

        return query.getResultList();
    }
}
