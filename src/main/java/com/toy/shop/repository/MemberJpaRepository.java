package com.toy.shop.repository;

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
    public List<Member> findAll(String searchWord) {
        String jpql = "select m from Member m";
        ArrayList<String> whereCondition = new ArrayList<>();

        if (StringUtils.hasText(searchWord)) {
            whereCondition.add("m.name like concat('%', :searchWord, '%')");
        }

        if (!whereCondition.isEmpty()) {
            jpql += " where ";
            jpql += String.join(" and ", whereCondition);
        }

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);

        if (StringUtils.hasText(searchWord)) {
            query.setParameter("searchWord", searchWord);
        }

        return query.getResultList();
    }
}
