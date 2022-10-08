package com.toy.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BookQueryRepository {

    private final JPAQueryFactory query;

    public BookQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
