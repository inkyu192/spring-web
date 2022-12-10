package com.toy.shop.repository;

import com.toy.shop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSpringJpaRepository extends JpaRepository<Member, Long> {
}
