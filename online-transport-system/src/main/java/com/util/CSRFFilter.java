package com.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that automatically ensures CSRF tokens exist in user sessions
 * and validates state-changing requests (POST, PUT, DELETE, PATCH).
 */
public class CSRFFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Ensure session exists and has a CSRF token
            String token = CSRFUtils.getToken(httpRequest);
            httpRequest.setAttribute("csrfToken", token);

            // For state-changing HTTP methods, validate CSRF token
            String method = httpRequest.getMethod();
            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)
                    || "DELETE".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {

                if (!CSRFUtils.isValidToken(httpRequest)) {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "CSRF token validation failed. Possible Cross-Site Request Forgery detected.");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup
    }
}

