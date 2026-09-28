package com.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Servlet filter that rewrites every {@code Set-Cookie} header to append
 * {@code SameSite=Strict} when the attribute is not already present.
 *
 * <p>This addresses the OWASP ZAP finding "Cookie without SameSite Attribute"
 * (CWE-1275).</p>
 */
public class SameSiteCookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialisation required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse) {
            SameSiteResponseWrapper wrappedResponse =
                    new SameSiteResponseWrapper((HttpServletResponse) response);
            try {
                chain.doFilter(request, wrappedResponse);
            } finally {
                wrappedResponse.patchCookieHeaders();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // No cleanup required
    }

    /**
     * Response wrapper that intercepts {@code Set-Cookie} headers and appends
     * {@code SameSite=Strict} when the attribute is absent.
     */
    private static final class SameSiteResponseWrapper extends HttpServletResponseWrapper {

        SameSiteResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public void addCookie(Cookie cookie) {
            if (cookie != null) {
                cookie.setAttribute("SameSite", "Strict");
            }
            super.addCookie(cookie);
        }

        @Override
        public void addHeader(String name, String value) {
            if ("Set-Cookie".equalsIgnoreCase(name) && value != null && !value.toLowerCase().contains("samesite")) {
                value = value + "; SameSite=Strict";
            }
            super.addHeader(name, value);
        }

        @Override
        public void setHeader(String name, String value) {
            if ("Set-Cookie".equalsIgnoreCase(name) && value != null && !value.toLowerCase().contains("samesite")) {
                value = value + "; SameSite=Strict";
            }
            super.setHeader(name, value);
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {
            patchCookieHeaders();
            super.sendError(sc, msg);
        }

        @Override
        public void sendError(int sc) throws IOException {
            patchCookieHeaders();
            super.sendError(sc);
        }

        @Override
        public void sendRedirect(String location) throws IOException {
            patchCookieHeaders();
            super.sendRedirect(location);
        }

        @Override
        public void flushBuffer() throws IOException {
            patchCookieHeaders();
            super.flushBuffer();
        }

        public void patchCookieHeaders() {
            Collection<String> headers = getHeaders("Set-Cookie");
            if (headers == null || headers.isEmpty()) {
                return;
            }

            List<String> patched = new ArrayList<>();
            for (String header : headers) {
                if (header != null && !header.toLowerCase().contains("samesite")) {
                    header = header + "; SameSite=Strict";
                }
                patched.add(header);
            }

            for (int i = 0; i < patched.size(); i++) {
                if (i == 0) {
                    setHeader("Set-Cookie", patched.get(i));
                } else {
                    addHeader("Set-Cookie", patched.get(i));
                }
            }
        }
    }
}

