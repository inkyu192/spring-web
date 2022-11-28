package com.toy.shop.service;

import com.toy.shop.domain.Member;
import com.toy.shop.dto.MemberResponseDto;
import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;
import com.toy.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto save(MemberSaveRequestDto requestDto) {
        Member member = Member.createMember(requestDto);

        memberRepository.save(member);

        return new MemberResponseDto(member);
    }

    @Override
    public MemberResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<MemberResponseDto> findAll(String searchWord) {
        return null;
    }

    @Override
    public MemberResponseDto update(Long id, MemberUpdateRequestDto requestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
