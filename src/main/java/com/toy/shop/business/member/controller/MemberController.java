package com.toy.shop.business.member.controller;

import com.toy.shop.business.member.dto.request.MemberLoginRequest;
import com.toy.shop.business.member.dto.request.MemberSaveRequest;
import com.toy.shop.business.member.dto.request.MemberUpdateRequest;
import com.toy.shop.business.member.dto.response.MemberResponse;
import com.toy.shop.business.member.service.MemberService;
import com.toy.shop.common.ApiResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("login")
    public Object login(@RequestBody MemberLoginRequest requestDto) {
        return memberService.login(requestDto);
    }

    @PostMapping
    public Object saveMember(@RequestBody @Valid MemberSaveRequest requestDto) {
        MemberResponse responseDto = memberService.saveMember(requestDto);

        return new ApiResponseDto<>(responseDto);
    }

    @GetMapping
    public Object members(@RequestParam(required = false) String name, Pageable pageable) {
        Page<MemberResponse> members = memberService.members(name, pageable);

        return new ApiResponseDto<>(members);
    }

    @GetMapping("{id}")
    public Object member(@PathVariable Long id) {
        MemberResponse responseDto = memberService.member(id);

        return new ApiResponseDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateMember(@PathVariable Long id,
                               @RequestBody @Valid MemberUpdateRequest requestDto) {
        MemberResponse responseDto = memberService.updateMember(id, requestDto);

        return new ApiResponseDto<>(responseDto);
    }

    @DeleteMapping("{id}")
    public Object deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return new ApiResponseDto<>();
    }
}