package spring.web.kotlin.presentation.exception

import org.springframework.http.HttpStatus

abstract class AbstractHttpException(
    message: String,
    val httpStatus: HttpStatus,
): RuntimeException(message) {
}