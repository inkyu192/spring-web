package com.toy.shop.repository;

import com.toy.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager entityManager;

    public Member save(Member member) {
        if (member.getId() == null) {
            entityManager.persist(member);
            return member;
        } else {
            return entityManager.merge(member);
        }
    }

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

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    public void delete(Member member) {
        entityManager.remove(member);
    }
}
