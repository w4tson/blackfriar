package com.blackfriar.web.security.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.nonNull;

/**
 * Created by paulwatson on 14/02/2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String originHeader = request.getHeader("Origin");
        if (nonNull(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", encodeHeader(originHeader));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
        }
      filterChain.doFilter(request, response);
    }

    private String encodeHeader(String header) throws UnsupportedEncodingException {
        return URLEncoder.encode(header, StandardCharsets.UTF_8.displayName());
    }
}
