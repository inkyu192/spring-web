package spring.webmvc.domain.model.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeliveryStatus {
	READY("준비"),
	CANCEL("취소"),
	COMP("완료");

	private final String description;

	@JsonCreator
	public static DeliveryStatus of(String name) {
		return Arrays.stream(DeliveryStatus.values())
			.filter(status -> status.name().equals(name))
			.findFirst()
			.orElse(null);
	}
}
