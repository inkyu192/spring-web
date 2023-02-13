package com.toy.shop.service.member;

import com.toy.shop.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto.Response saveMember(MemberDto.Save requestDto);

    List<MemberDto.Response> members(String name);

    MemberDto.Response member(Long id);

    MemberDto.Response updateMember(Long id, MemberDto.Update requestDto);

    void deleteMember(Long id);
}
