package com.example.jwtdemo.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Order(1)   // Run this filter first
public class AuditLogFilter extends OncePerRequestFilter {

    // Named logger routes to AUDIT_FILE defined in logback-spring.xml
    private static final Logger AUDIT_LOG =
            LoggerFactory.getLogger("AUDIT_LOGGER");

    private static final Logger APP_LOG =
            LoggerFactory.getLogger(AuditLogFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest  request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain         filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // Let the rest of the filter chain run (including JWT auth)
        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        // After response is committed, resolve authenticated username
        String username = resolveUsername();

        // Build audit log entry
        String auditEntry = String.format(
            "[AUDIT] %s | user=%-15s | method=%-6s | uri=%-40s | status=%d | duration=%dms",
            LocalDateTime.now(),
            username,
            request.getMethod(),
            request.getRequestURI(),
            response.getStatus(),
            duration
        );

        // Route to correct log level based on HTTP status
        int status = response.getStatus();
        if (status >= 500) {
            AUDIT_LOG.error(auditEntry);
        } else if (status >= 400) {
            AUDIT_LOG.warn(auditEntry);
        } else {
            AUDIT_LOG.info(auditEntry);
        }

        APP_LOG.debug("Request completed in {}ms for URI: {}",
                      duration, request.getRequestURI());
    }

    /**
     * Safely resolve the authenticated username from the SecurityContext.
     * Returns "anonymous" if the request is unauthenticated.
     */
    private String resolveUsername() {
        try {
            Authentication auth =
                    SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()
                    && !"anonymousUser".equals(auth.getPrincipal())) {
                return auth.getName();
            }
        } catch (Exception e) {
            APP_LOG.warn("Could not resolve username from SecurityContext", e);
        }
        return "anonymous";
    }
}