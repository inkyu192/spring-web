package com.toy.shop.service;

import com.toy.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSpringJpaRepository extends JpaRepository<Order, Long> {
}
