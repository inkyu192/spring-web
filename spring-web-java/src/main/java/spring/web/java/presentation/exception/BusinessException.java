package spring.web.java.presentation.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

	private final HttpStatus httpStatus;

	public BusinessException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
