<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    
</head>
<body>
    <%
        // Check if user is logged in
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="container">
        <a href="logout" class="logout">Logout</a>
        <h2>Welcome, <%= user.getUsername() %>!</h2>
        <div class="user-info">
            <p><strong>Username:</strong> <%= user.getUsername() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>User ID:</strong> <%= user.getId() %></p>
        </div>
    </div>
</body>
</html>
