<!-- filepath: e:\HCMUTE\HKI nam 3\LTWEB\BT\BaiTap\src\main\webapp\forget-password.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forget Password</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Forget Password</h2>
        <% if(request.getAttribute("error") != null) { %>
            <p class="error-message"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if(request.getAttribute("success") != null) { %>
            <p class="success-message"><%= request.getAttribute("success") %></p>
        <% } %>
        <form action="forget-password" method="post">
            <div>
                <label for="email">Enter your email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit">Send Reset Link</button>
        </form>
        <div class="login-link">
            <p>Back to <a href="login.jsp">Login</a></p>
        </div>
    </div>
</body>
</html>