package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.MemberResponseDto;
import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;
import com.toy.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Object saveMember(@RequestBody @Valid MemberSaveRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object members(@RequestParam(required = false) String searchWord) {
        List<MemberResponseDto> list = memberService.findAll(searchWord);

        return new ResultDto<>(list);
    }

    @GetMapping("{id}")
    public Object member(@PathVariable Long id) {
        MemberResponseDto responseDto = memberService.findById(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateMember(@PathVariable Long id, @RequestBody @Valid MemberUpdateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.update(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("{id}")
    public Object deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return new ResultDto<>();
    }
}
