package com.toy.shop.service;

import com.toy.shop.dto.MemberResponseDto;
import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;

import java.util.List;

public interface MemberService {

    MemberResponseDto save(MemberSaveRequestDto requestDto);

    MemberResponseDto findById(Long id);

    List<MemberResponseDto> findAll(String searchWord);

    MemberResponseDto update(Long id, MemberUpdateRequestDto requestDto);

    void delete(Long id);
}
