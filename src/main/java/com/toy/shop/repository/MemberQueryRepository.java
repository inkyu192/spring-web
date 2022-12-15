package com.toy.shop.repository;

import com.toy.shop.domain.Member;

import java.util.List;

public interface MemberQueryRepository {

    List<Member> findAll(String searchWord);
}
