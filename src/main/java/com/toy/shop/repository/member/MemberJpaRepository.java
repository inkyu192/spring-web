package com.toy.shop.repository.member;

import com.toy.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<Member> findAll(String name) {
        String jpql = "select m from Member m";

        ArrayList<String> whereCondition = new ArrayList<>();

        if (StringUtils.hasText(name)) whereCondition.add("m.name like concat('%', :name, '%')");

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);

        if (StringUtils.hasText(name)) query.setParameter("name", name);

        return query.getResultList();
    }
}
