package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategorySpringJpaRepository extends JpaRepository<Category, Long>, CategoryQueryRepository {
}
