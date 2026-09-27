package com.Logins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import com.util.PasswordUtils;

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

    private static class AttemptState {
        final int failedAttempts;
        final long lastFailureAt;
        final long lockedUntil;

        AttemptState(int failedAttempts, long lastFailureAt, long lockedUntil) {
            this.failedAttempts = failedAttempts;
            this.lastFailureAt = lastFailureAt;
            this.lockedUntil = lockedUntil;
        }
    }

    public AuthenticationResult authenticateUser(String un, String pw) {
        if (un == null || pw == null) {
            return AuthenticationResult.INVALID_CREDENTIALS;
        }
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

    public boolean validateUser(String un, String pw) {
        return authenticateUser(un, pw) == AuthenticationResult.SUCCESS;
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
                query = "SELECT password FROM RegisterDetails WHERE userName = ?";
            } else if (un.startsWith("DD")) {
                query = "SELECT password FROM driver_Details WHERE name = ? AND status = 'Accepted'";
            } else if (un.startsWith("AD")) {
                query = "SELECT password FROM RegisterDetails WHERE userName = ? AND status = 'Accepted'";
            }

            if (query == null) {
                return false;
            }

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, un);

            rs = pst.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                status = PasswordUtils.verifyPassword(storedHash, pw);
            }

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
}