package com.toy.shop.repository;

import com.toy.shop.domain.Member;

import java.util.List;

public interface MemberCustomRepository {

    List<Member> findAllByJpql(String searchWord);

    List<Member> findAllByQuery(String searchWord);
}
