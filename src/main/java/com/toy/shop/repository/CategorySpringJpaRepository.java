package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorySpringJpaRepository extends JpaRepository<Category, Long> {
}
