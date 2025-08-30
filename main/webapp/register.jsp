<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        
        <% if(request.getAttribute("error") != null) { %>
            <p class="error-message"><%= request.getAttribute("error") %></p>
        <% } %>
        <% if(request.getAttribute("success") != null) { %>
            <p class="success-message"><%= request.getAttribute("success") %></p>
        <% } %>
        
        <form action="register" method="post">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required
                       value="<%= request.getAttribute("username") != null ? 
                               request.getAttribute("username") : 
                               (request.getParameter("username") != null ? request.getParameter("username") : "") %>">
                <small>Username must be 3-20 characters, letters and numbers only</small>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required
                       value="<%= request.getAttribute("email") != null ? 
                               request.getAttribute("email") : 
                               (request.getParameter("email") != null ? request.getParameter("email") : "") %>">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <small>Password must be at least 6 characters</small>
            </div>
            <div>
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div>
                <button type="submit">Register</button>
            </div>
        </form>
        <p>Already have an account? <a href="login">Login here</a></p>
    </div>
</body>
</html>
