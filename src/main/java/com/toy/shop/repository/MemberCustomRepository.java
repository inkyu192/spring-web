package com.toy.shop.repository;

import com.toy.shop.domain.Member;

import java.util.List;

public interface MemberCustomRepository {

    List<Member> findAllOfJpql(String searchWord);

    List<Member> findAllOfQuery(String searchWord);
}
