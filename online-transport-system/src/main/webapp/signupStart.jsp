<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.util.XSSUtils" %>
<%@ page import="com.util.CSRFUtils" %>
<%
    String rawRole = request.getParameter("role");
    String role = "Passenger";
    if ("Admin".equals(rawRole) || "Driver".equals(rawRole) || "Passenger".equals(rawRole)) {
        role = rawRole;
    }
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Start Signup</title>
<style nonce="${cspNonce}">
body { font-family: Arial, sans-serif; background: #eef4f7; display: grid; place-items: center; min-height: 100vh; margin: 0; }
.panel { width: min(420px, calc(100% - 36px)); padding: 30px; background: white; border-radius: 10px; box-shadow: 0 8px 24px rgba(0,0,0,.12); }
h1 { margin-top: 0; color: #16324f; } label { display: block; margin-top: 16px; font-weight: bold; }
input, button, .google { box-sizing: border-box; width: 100%; min-height: 44px; margin-top: 7px; padding: 10px 12px; border: 1px solid #b9c4cc; border-radius: 6px; font-size: 15px; }
button { background: #39cda6; color: white; border: 0; font-weight: bold; cursor: pointer; }
.google { display: block; text-align: center; text-decoration: none; color: #222; background: white; }
.error { color: #b42318; background: #fff1f0; padding: 10px; border-radius: 5px; }
.or { text-align: center; color: #777; margin: 16px 0; }
</style></head>
<body><main class="panel">
<h1>Create <%= XSSUtils.sanitize(role) %> account</h1>
<% if (errorMessage != null) { %><div class="error"><%= XSSUtils.sanitize(errorMessage) %></div><% } %>
<form action="signupStart" method="post">
<input type="hidden" name="csrfToken" value="<%= CSRFUtils.getToken(request) %>" />
<input type="hidden" name="role" value="<%= XSSUtils.sanitizeForHtmlAttribute(role) %>">
<label for="email">Email address</label><input id="email" type="email" name="email" required maxlength="255">
<label for="password">Password</label><input id="password" type="password" name="password" required>
<button type="submit">Continue with email</button>
</form>
<div class="or">or</div>
<a class="google" href="google-login?mode=signup&amp;role=<%= XSSUtils.sanitizeForHtmlAttribute(role) %>">Continue with Google</a>
</main></body></html>