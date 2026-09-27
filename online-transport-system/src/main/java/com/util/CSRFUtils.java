package com.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for Cross-Site Request Forgery (CSRF) protection.
 */
public class CSRFUtils {

    public static final String CSRF_TOKEN_ATTR = "csrfToken";
    private static final SecureRandom random = new SecureRandom();

    private CSRFUtils() {
        // Utility class
    }

    /**
     * Retrieves the CSRF token from the current HTTP session.
     * If no token exists in the session, a new secure token is generated and stored in the session.
     *
     * @param request the HttpServletRequest
     * @return the CSRF token string
     */
    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        HttpSession session = request.getSession(true);
        String token = (String) session.getAttribute(CSRF_TOKEN_ATTR);
        if (token == null || token.trim().isEmpty()) {
            token = generateToken();
            session.setAttribute(CSRF_TOKEN_ATTR, token);
        }
        return token;
    }

    /**
     * Validates the CSRF token submitted in the request parameter against the token stored in the session.
     *
     * @param request the HttpServletRequest
     * @return true if valid token match; false otherwise
     */
    public static boolean isValidToken(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        String sessionToken = (String) session.getAttribute(CSRF_TOKEN_ATTR);
        if (sessionToken == null || sessionToken.trim().isEmpty()) {
            return false;
        }
        String requestToken = request.getParameter(CSRF_TOKEN_ATTR);
        if (requestToken == null || requestToken.trim().isEmpty()) {
            return false;
        }
        return constantTimeEquals(sessionToken, requestToken);
    }

    /**
     * Constant-time string comparison to prevent timing attacks.
     */
    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }

    /**
     * Generates a cryptographically secure random token string.
     */
    private static String generateToken() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
