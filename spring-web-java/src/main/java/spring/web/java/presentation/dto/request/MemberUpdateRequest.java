package spring.web.java.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record MemberUpdateRequest(
	@NotNull
	String name,
	@NotNull
	String city,
	@NotNull
	String street,
	@NotNull
	String zipcode
) {
}
