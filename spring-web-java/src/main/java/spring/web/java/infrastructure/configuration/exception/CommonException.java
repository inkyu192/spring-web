package spring.web.java.infrastructure.configuration.exception;

import lombok.Getter;
import spring.web.java.common.ResponseMessage;

@Getter
public class CommonException extends RuntimeException {

	private final ResponseMessage responseMessage;

	public CommonException(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
}
