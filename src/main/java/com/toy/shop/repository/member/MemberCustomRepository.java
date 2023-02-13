package com.toy.shop.repository.member;

import com.toy.shop.domain.Member;

import java.util.List;

public interface MemberCustomRepository {

    List<Member> findAll(String searchWord);
}
