package com.toy.shop.repository;

import com.toy.shop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSpringJpaRepository extends JpaRepository<Item, Long>, ItemQueryRepository {
}
