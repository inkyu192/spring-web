package spring.web.java.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record TokenRequest(
        @NotNull
        String accessToken,
        @NotNull
        String refreshToken
) {
}
