package spring.web.java.infrastructure.configuration.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import spring.web.java.common.ResponseMessage;

@Getter
public class DomainException extends BaseException {

	private final ResponseMessage responseMessage;

	public DomainException(ResponseMessage responseMessage, HttpStatus httpStatus) {
		super(responseMessage.getDetail(), httpStatus);
		this.responseMessage = responseMessage;
	}
}
