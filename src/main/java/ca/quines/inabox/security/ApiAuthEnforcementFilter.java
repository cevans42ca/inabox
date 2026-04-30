package ca.quines.inabox.security;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Simple guard to enforce that all /api/** endpoints require a Basic Authorization header.
 * This is a pragmatic layer ensuring a 401 is returned when no credentials are provided,
 * independent of the broader Spring Security setup. It does not validate credentials —
 * full authentication remains the responsibility of Spring Security when enabled.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiAuthEnforcementFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path != null && path.startsWith("/api/")) {
            String auth = request.getHeader("Authorization");
            if (auth == null || !auth.startsWith("Basic ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
