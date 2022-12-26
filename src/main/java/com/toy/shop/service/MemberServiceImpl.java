package com.toy.shop.service;

import com.toy.shop.domain.Member;
import com.toy.shop.exception.CommonException;
import com.toy.shop.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.toy.shop.common.ResultCode.MEMBER_NOT_FOUND;
import static com.toy.shop.dto.MemberDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberRepository;
//    private final MemberSpringJpaRepository memberRepository;

    @Override
    @Transactional
    public Response save(saveRequest requestDto) {
        Member member = Member.createMember(requestDto);

        memberRepository.save(member);

        return new Response(member);
    }

    @Override
    public List<Response> findAll(String searchWord) {
        List<Member> members = memberRepository.findAll(searchWord);

        return members.stream()
                .map(Response::new)
                .toList();
    }

    @Override
    public Response findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CommonException(MEMBER_NOT_FOUND));

        return new Response(member);
    }

    @Override
    @Transactional
    public Response update(Long id, UpdateRequest requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CommonException(MEMBER_NOT_FOUND));

        member.updateMember(requestDto);

        return new Response(member);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CommonException(MEMBER_NOT_FOUND));

        memberRepository.delete(member);
    }
}
