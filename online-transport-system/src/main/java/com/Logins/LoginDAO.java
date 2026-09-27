package com.Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class LoginDAO {
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCKOUT_DURATION_MILLIS = 5 * 60 * 1000;
    private static final ConcurrentHashMap<String, AttemptState> LOGIN_ATTEMPTS = new ConcurrentHashMap<>();
    private static final AtomicLong AUTHENTICATION_COUNT = new AtomicLong();

    public enum AuthenticationResult {
        SUCCESS,
        INVALID_CREDENTIALS,
        LOCKED
    }

    public AuthenticationResult authenticateUser(String un, String pw) {
        String accountKey = un.trim().toLowerCase(Locale.ROOT);
        long now = System.currentTimeMillis();
        AtomicReference<AuthenticationResult> result = new AtomicReference<>();

        LOGIN_ATTEMPTS.compute(accountKey, (key, previous) -> {
            AttemptState state = previous;
            if (state != null && ((state.lockedUntil > 0 && now >= state.lockedUntil)
                    || now - state.lastFailureAt >= LOCKOUT_DURATION_MILLIS)) {
                state = null;
            }

            if (state != null && state.lockedUntil > now) {
                result.set(AuthenticationResult.LOCKED);
                return state;
            }

            if (checkCredentials(un, pw)) {
                result.set(AuthenticationResult.SUCCESS);
                return null;
            }

            int failedAttempts = state == null ? 1 : state.failedAttempts + 1;
            if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
                result.set(AuthenticationResult.LOCKED);
                return new AttemptState(failedAttempts, now, now + LOCKOUT_DURATION_MILLIS);
            }

            result.set(AuthenticationResult.INVALID_CREDENTIALS);
            return new AttemptState(failedAttempts, now, 0);
        });

        if ((AUTHENTICATION_COUNT.incrementAndGet() & 127) == 0) {
            LOGIN_ATTEMPTS.entrySet().removeIf(entry ->
                    now - entry.getValue().lastFailureAt >= LOCKOUT_DURATION_MILLIS);
        }
        return result.get();
    }

    private boolean checkCredentials(String un, String pw) {
        boolean status = false;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = com.util.DBConfig.getConnection();
            String query = null;

            if (un.startsWith("CT")) {
                query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ?";
            } else if (un.startsWith("DD")) {
                query = "SELECT * FROM driver_Details WHERE name = ? AND password = ? AND status = 'Accepted'";
            } else if (un.startsWith("AD")) {
                query = "SELECT * FROM RegisterDetails WHERE userName = ? AND password = ? AND status = 'Accepted'";
            }

            if (query == null) {
                return false;
            }

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, un);
            pst.setString(2, hashPassword(pw));

            rs = pst.executeQuery();
            status = rs.next();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    private static final class AttemptState {
        private final int failedAttempts;
        private final long lastFailureAt;
        private final long lockedUntil;

        private AttemptState(int failedAttempts, long lastFailureAt, long lockedUntil) {
            this.failedAttempts = failedAttempts;
            this.lastFailureAt = lastFailureAt;
            this.lockedUntil = lockedUntil;
        }
    }

    private String hashPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(plainTextPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}