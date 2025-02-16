package spring.web.java.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import spring.web.java.presentation.dto.response.MemberResponse;
import spring.web.java.presentation.dto.request.MemberSaveRequest;
import spring.web.java.presentation.dto.request.MemberUpdateRequest;
import spring.web.java.application.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MemberResponse saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
		return memberService.saveMember(memberSaveRequest);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public MemberResponse findMember() {
		return memberService.findMember();
	}

	@PreAuthorize("isAuthenticated()")
	@PatchMapping
	public MemberResponse updateMember(@RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {
		return memberService.updateMember(memberUpdateRequest);
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember() {
		memberService.deleteMember();
	}
}
