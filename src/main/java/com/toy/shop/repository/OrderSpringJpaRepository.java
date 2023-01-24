package com.toy.shop.repository;

import com.toy.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSpringJpaRepository extends JpaRepository<Order, Long> {
}
