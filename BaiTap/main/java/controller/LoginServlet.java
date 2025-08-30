package controller;

import service.UserService;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("home.jsp");
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra đầu vào
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Trim input để loại bỏ khoảng trắng
        username = username.trim();
        
        // Giới hạn số lần đăng nhập sai (có thể lưu trong session)
        HttpSession session = request.getSession();
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0;
        }
        
        if (loginAttempts >= 5) {
            // Sau 5 lần đăng nhập sai, yêu cầu đợi
            long lastAttemptTime = session.getAttribute("lastLoginAttempt") != null ? 
                (long) session.getAttribute("lastLoginAttempt") : 0;
            long currentTime = System.currentTimeMillis();
            
            if (currentTime - lastAttemptTime < 60000) { // 1 phút
                request.setAttribute("error", "Too many failed login attempts. Please wait for a minute before trying again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                // Reset counter sau 1 phút
                session.setAttribute("loginAttempts", 0);
                loginAttempts = 0;
            }
        }
        
        boolean authenticated = userService.authenticateUser(username, password);

        if (authenticated) {
            // Reset login attempts counter
            session.setAttribute("loginAttempts", 0);
            
            // Lấy thông tin người dùng và lưu vào session
            User user = userService.getUserByUsername(username);
            session.setAttribute("user", user);
            
            // Chuyển hướng đến trang home
            response.sendRedirect("home.jsp");
        } else {
            // Tăng counter đăng nhập sai
            session.setAttribute("loginAttempts", loginAttempts + 1);
            session.setAttribute("lastLoginAttempt", System.currentTimeMillis());
            
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
