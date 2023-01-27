package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.DeliveryStatus;
import com.toy.shop.domain.Order;
import com.toy.shop.domain.OrderStatus;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.shop.domain.QDelivery.delivery;
import static com.toy.shop.domain.QMember.member;
import static com.toy.shop.domain.QOrder.order;

@Repository
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Order> findAll(Long memberId, OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        return queryFactory
                .select(order)
                .from(order)
                .join(order.member, member)
                .fetchJoin()
                .join(order.delivery, delivery)
                .fetchJoin()
                .where(
                        memberId(memberId),
                        orderStatus(orderStatus),
                        deliveryStatus(deliveryStatus)
                )
                .fetch();
    }

    private BooleanExpression memberId(Long memberId) {
        if (memberId != null) {
            return member.id.eq(memberId);
        }
        return null;
    }

    private BooleanExpression orderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            return order.status.eq(orderStatus);
        }
        return null;
    }

    private BooleanExpression deliveryStatus(DeliveryStatus deliveryStatus) {
        if (deliveryStatus != null) {
            return delivery.status.eq(deliveryStatus);
        }
        return null;
    }
}
