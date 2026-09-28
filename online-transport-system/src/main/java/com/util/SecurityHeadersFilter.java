package com.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Filter that adds security HTTP response headers to every response to mitigate
 * common web vulnerabilities identified by OWASP ZAP:
 *
 * <ul>
 *   <li>Content-Security-Policy  – mitigates XSS and data-injection (CWE-693)</li>
 *   <li>X-Frame-Options          – prevents clickjacking (CWE-1021)</li>
 *   <li>X-Content-Type-Options   – prevents MIME-sniffing (CWE-693)</li>
 *   <li>Referrer-Policy          – limits referrer information leakage</li>
 *   <li>Strict-Transport-Security – enforces HTTPS (informational, harmless on HTTP)</li>
 * </ul>
 */
public class SecurityHeadersFilter implements Filter {

    private static final SecureRandom random = new SecureRandom();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No initialisation required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Generate cryptographic random nonce for CSP
            byte[] nonceBytes = new byte[16];
            random.nextBytes(nonceBytes);
            String nonce = Base64.getEncoder().encodeToString(nonceBytes);
            httpRequest.setAttribute("cspNonce", nonce);

            SecurityHeadersResponseWrapper wrappedResponse =
                    new SecurityHeadersResponseWrapper(httpResponse, nonce);

            // Apply security headers
            wrappedResponse.applyHeaders();

            try {
                chain.doFilter(request, wrappedResponse);
            } finally {
                // Re-apply security headers in case of internal container processing
                wrappedResponse.applyHeaders();
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
     * Response wrapper that ensures security headers are preserved across
     * errors, redirects, and flushes.
     */
    private static class SecurityHeadersResponseWrapper extends HttpServletResponseWrapper {
        private final String nonce;

        public SecurityHeadersResponseWrapper(HttpServletResponse response, String nonce) {
            super(response);
            this.nonce = nonce;
        }

        public void applyHeaders() {
            String csp = "default-src 'self'; "
                    + "script-src 'self' 'nonce-" + nonce + "' https://cdn.jsdelivr.net; "
                    + "style-src 'self' 'nonce-" + nonce + "' https://fonts.googleapis.com https://cdn.pixabay.com https://media.gettyimages.com https://cdn.jsdelivr.net; "
                    + "img-src 'self' data: https:; "
                    + "font-src 'self' https://fonts.gstatic.com; "
                    + "frame-src 'self' https://www.google.com; "
                    + "frame-ancestors 'none'; "
                    + "object-src 'none'; "
                    + "form-action 'self'; "
                    + "base-uri 'self';";

            setHeader("Content-Security-Policy", csp);
            setHeader("X-Frame-Options", "DENY");
            setHeader("X-Content-Type-Options", "nosniff");
            setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
            setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {
            applyHeaders();
            super.sendError(sc, msg);
        }

        @Override
        public void sendError(int sc) throws IOException {
            applyHeaders();
            super.sendError(sc);
        }

        @Override
        public void sendRedirect(String location) throws IOException {
            applyHeaders();
            super.sendRedirect(location);
        }

        @Override
        public void flushBuffer() throws IOException {
            applyHeaders();
            super.flushBuffer();
        }
    }
}

