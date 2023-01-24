package com.toy.shop.repository;

import com.toy.shop.domain.DeliveryStatus;
import com.toy.shop.domain.Order;
import com.toy.shop.domain.OrderStatus;

import java.util.List;

public interface OrderQueryRepository {

    List<Order> findAll(Long memberId, OrderStatus orderStatus, DeliveryStatus deliveryStatus);
}
