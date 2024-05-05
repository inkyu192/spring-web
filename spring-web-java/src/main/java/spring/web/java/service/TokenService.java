package spring.web.java.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.java.config.security.JwtTokenProvider;
import spring.web.java.constant.ApiResponseCode;
import spring.web.java.domain.Token;
import spring.web.java.dto.request.TokenRequest;
import spring.web.java.dto.response.TokenResponse;
import spring.web.java.exception.CommonException;
import spring.web.java.repository.TokenRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public TokenResponse reissue(TokenRequest tokenRequest) {
        Claims claims;

        try {
            claims = jwtTokenProvider.parseAccessToken(tokenRequest.accessToken());
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }

        String account = (String) claims.get("account");
        String authorities = claims.get("authorities").toString();

        String refreshToken = tokenRepository.findById(account)
                .map(Token::getRefreshToken)
                .orElseThrow(() -> new CommonException(ApiResponseCode.BAD_CREDENTIALS));

        if (!tokenRequest.refreshToken().equals(refreshToken)) {
            throw new CommonException(ApiResponseCode.BAD_CREDENTIALS);
        }

        return new TokenResponse(jwtTokenProvider.createAccessToken(account, authorities));
    }
}
