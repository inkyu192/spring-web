package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c where c.name like %:searchWord%")
    List<Category> findAll(@Param("searchWord") String searchWord);

    List<Category> findByNameContaining(String searchWord);
}
