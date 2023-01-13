package com.toy.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toy.shop.domain.QMember.member;


@Repository
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Member> findAll(String name) {
        return queryFactory.select(member)
                .from(member)
                .where(likeSearchWord(name))
                .fetch();
    }

    private BooleanExpression likeSearchWord(String name) {
        if (StringUtils.hasText(name)) {
            return member.name.like("%" + name + "%");
        }

        return null;
    }
}
