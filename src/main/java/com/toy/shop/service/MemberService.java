package com.toy.shop.service;

import com.toy.shop.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto.Response save(MemberDto.saveRequest requestDto);

    List<MemberDto.Response> findAll(String searchWord);

    MemberDto.Response findById(Long id);

    MemberDto.Response update(Long id, MemberDto.UpdateRequest requestDto);

    void delete(Long id);
}
