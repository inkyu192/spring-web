package com.toy.shop.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.shop.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.toy.shop.domain.QMember.member;


@Primary
@Repository
public class MemberQueryRepository implements MemberCustomRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Member> findAll(String name) {
        return queryFactory
                .select(member)
                .from(member)
                .where(name(name))
                .fetch();
    }

    private BooleanExpression name(String name) {
        if (StringUtils.hasText(name)) {
            return member.name.like("%" + name + "%");
        }

        return null;
    }
}
