package com.toy.shopwebmvc.controller;

import com.toy.shopwebmvc.dto.request.MemberSaveRequest;
import com.toy.shopwebmvc.dto.request.MemberUpdateRequest;
import com.toy.shopwebmvc.dto.response.MemberResponse;
import com.toy.shopwebmvc.service.MemberService;
import com.toy.shopwebmvc.dto.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberResponse> saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        MemberResponse responseDto = memberService.saveMember(memberSaveRequest);

        return new ApiResponse<>(responseDto);
    }

    @GetMapping
    public ApiResponse<Page<MemberResponse>> members(Pageable pageable, @RequestParam(required = false) String name) {
        Page<MemberResponse> members = memberService.members(name, pageable);

        return new ApiResponse<>(members);
    }

    @GetMapping("{id}")
    public ApiResponse<MemberResponse> member(@PathVariable Long id) {
        MemberResponse responseDto = memberService.member(id);

        return new ApiResponse<>(responseDto);
    }

    @PatchMapping("{id}")
    public ApiResponse<MemberResponse> updateMember(
            @PathVariable Long id,
            @RequestBody @Valid MemberUpdateRequest memberUpdateRequest
    ) {
        MemberResponse responseDto = memberService.updateMember(id, memberUpdateRequest);

        return new ApiResponse<>(responseDto);
    }

    @DeleteMapping("{id}")
    public ApiResponse<MemberResponse> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return new ApiResponse<>();
    }
}