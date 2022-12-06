package com.toy.shop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("======================== Filter init ========================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String logId = UUID.randomUUID().toString();

        try {
            log.info("======================== Filter request [{}][{}] ========================", logId, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("======================== Filter error [{}] ========================", e.getMessage(), e);
            throw e;
        } finally {
            log.info("======================== Filter response [{}][{}] ========================", logId, requestURI);
        }

    }

    @Override
    public void destroy() {
        log.info("======================== Filter destroy ========================");
    }
}
