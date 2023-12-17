package com.toy.shopwebmvc.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.shopwebmvc.constant.ApiResponseCode;
import com.toy.shopwebmvc.dto.response.ApiResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setResponse(response, e);
        }
    }

    private void setResponse(HttpServletResponse response, JwtException e) throws RuntimeException, IOException {
        String result = objectMapper.writeValueAsString(new ApiResponse<>(ApiResponseCode.BAD_REQUEST, e.getMessage()));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}