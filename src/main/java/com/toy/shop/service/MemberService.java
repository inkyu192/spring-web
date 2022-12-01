package com.toy.shop.service;

import com.toy.shop.dto.MemberResponseDto;
import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;

import java.util.List;

public interface MemberService {

    MemberResponseDto save(MemberSaveRequestDto requestDto);

    List<MemberResponseDto> findAll(String searchWord);

    MemberResponseDto findById(Long id);

    MemberResponseDto update(Long id, MemberUpdateRequestDto requestDto);

    void delete(Long id);
}
