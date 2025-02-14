package spring.web.java.domain.model.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

	ROLE_BOOK("책"),
	ROLE_TICKET("표");

	private final String description;

	@JsonCreator
	public static Category of(Object name) {
		return Arrays.stream(Category.values())
			.filter(category -> category.name().equals(name))
			.findFirst()
			.orElse(null);
	}
}
