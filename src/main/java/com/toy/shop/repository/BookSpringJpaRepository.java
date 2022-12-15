package com.toy.shop.repository;

import com.toy.shop.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSpringJpaRepository extends JpaRepository<Book, Long>, BookQueryRepository {
}
