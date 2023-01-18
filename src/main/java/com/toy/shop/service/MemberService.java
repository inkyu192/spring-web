package com.toy.shop.service;

import com.toy.shop.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto.Response saveMember(MemberDto.saveRequest requestDto);

    List<MemberDto.Response> members(String name);

    MemberDto.Response member(Long id);

    MemberDto.Response updateMember(Long id, MemberDto.UpdateRequest requestDto);

    void deleteMember(Long id);
}
