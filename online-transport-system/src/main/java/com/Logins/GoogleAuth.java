package com.Logins;

import com.util.DBConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet({"/google-login", "/google-callback"})
public class GoogleAuth extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String REDIRECT_URI = "http://localhost:8080/OnlineTransportSystem/google-callback";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if ("/google-login".equals(request.getServletPath())) {
            startLogin(request, response);
        } else {
            completeLogin(request, response);
        }
    }

    private void startLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clientId = setting("GOOGLE_CLIENT_ID");
        if (clientId == null || setting("GOOGLE_CLIENT_SECRET") == null) {
            showError(request, response, "Google login is not configured on the server.");
            return;
        }
        String state = UUID.randomUUID().toString();
        request.getSession(true).setAttribute("googleOAuthState", state);
        String location = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + encode(clientId)
                + "&redirect_uri=" + encode(REDIRECT_URI)
                + "&response_type=code&scope=" + encode("openid email profile")
                + "&state=" + encode(state);
        response.sendRedirect(location);
    }

    private void completeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String expectedState = session == null ? null : (String) session.getAttribute("googleOAuthState");
        if (session != null) {
            session.removeAttribute("googleOAuthState");
        }
        String actualState = value(request.getParameter("state"));
        if (expectedState == null || !MessageDigest.isEqual(expectedState.getBytes(StandardCharsets.UTF_8), actualState.getBytes(StandardCharsets.UTF_8))) {
            showError(request, response, "Google login could not be verified. Please try again.");
            return;
        }
        if (request.getParameter("error") != null) {
            showError(request, response, "Google login was cancelled.");
            return;
        }

        String clientId = setting("GOOGLE_CLIENT_ID");
        String clientSecret = setting("GOOGLE_CLIENT_SECRET");
        String code = value(request.getParameter("code"));
        if (clientId == null || clientSecret == null || code.isEmpty()) {
            showError(request, response, "Google login is not configured correctly.");
            return;
        }

        try {
            String tokenResponse = postToken(code, clientId, clientSecret);
            String idToken = jsonValue(tokenResponse, "id_token");
            if (idToken == null) {
                showError(request, response, "Google did not return a valid identity token.");
                return;
            }
            String identity = get("https://oauth2.googleapis.com/tokeninfo?id_token=" + encode(idToken));
            String email = jsonValue(identity, "email");
            String audience = jsonValue(identity, "aud");
            String verified = jsonValue(identity, "email_verified");
            if (email == null || !clientId.equals(audience) || !"true".equalsIgnoreCase(verified)) {
                showError(request, response, "Google account verification failed.");
                return;
            }
            String userName = findOrCreateCustomer(email);
            request.getSession(true).setAttribute("userName", userName);
            response.sendRedirect("cusHome.jsp");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            showError(request, response, "Google login was interrupted.");
        } catch (IOException | SQLException exception) {
            showError(request, response, "Google login could not be completed.");
        }
    }

    private String postToken(String code, String clientId, String clientSecret) throws IOException, InterruptedException {
        String body = "code=" + encode(code) + "&client_id=" + encode(clientId) + "&client_secret="
                + encode(clientSecret) + "&redirect_uri=" + encode(REDIRECT_URI) + "&grant_type=authorization_code";
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://oauth2.googleapis.com/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private String get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> result = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return result.statusCode() == 200 ? result.body() : "{}";
    }

    private String findOrCreateCustomer(String email) throws SQLException {
        try (Connection connection = DBConfig.getConnection()) {
            try (PreparedStatement lookup = connection.prepareStatement("SELECT userName FROM RegisterDetails WHERE email = ?")) {
                lookup.setString(1, email);
                try (ResultSet result = lookup.executeQuery()) {
                    if (result.next()) {
                        return result.getString(1);
                    }
                }
            }
            String userName = "CT" + randomCustomerId();
            String password = hash(UUID.randomUUID().toString());
            try (PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO RegisterDetails(userName, gender, email, password, phone, role, address, comments, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                insert.setString(1, userName);
                insert.setString(2, "Other");
                insert.setString(3, email);
                insert.setString(4, password);
                insert.setString(5, "0000000000");
                insert.setString(6, "Customer");
                insert.setString(7, "Google account");
                insert.setString(8, "Created with Google");
                insert.setString(9, "Accepted");
                insert.executeUpdate();
            }
            return userName;
        }
    }

    private String randomCustomerId() {
        byte[] bytes = new byte[6];
        RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).toUpperCase(Locale.ROOT);
    }

    private String hash(String value) {
        try {
            return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to create account", exception);
        }
    }

    private static String jsonValue(String json, String key) {
        String expression = "\"" + key + "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"";
        Matcher matcher = Pattern.compile(expression).matcher(json);
        return matcher.find() ? matcher.group(1).replace("\\\"", "\"").replace("\\\\", "\\") : null;
    }

    private static String setting(String name) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? System.getProperty(name) : value;
    }

    private static String encode(String value) { return URLEncoder.encode(value, StandardCharsets.UTF_8); }
    private static String value(String value) { return value == null ? "" : value; }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}