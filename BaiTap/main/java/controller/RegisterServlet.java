package controller;

import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra các trường đã được điền đầy đủ chưa
        if (username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra username có hợp lệ không (chỉ chấp nhận chữ và số, từ 3-20 ký tự)
        if (!username.trim().matches("^[a-zA-Z0-9]{3,20}$")) {
            request.setAttribute("error", "Username must be between 3-20 characters and contain only letters and numbers");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra email có hợp lệ không
        if (!email.trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            request.setAttribute("error", "Please enter a valid email address");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra mật khẩu có đủ mạnh không (ít nhất 6 ký tự)
        if (password.length() < 6) {
            request.setAttribute("error", "Password must be at least 6 characters long");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp không
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Đăng ký người dùng mới
        boolean success = userService.registerUser(username, email, password);

        if (success) {
            response.sendRedirect("login.jsp?registered=true");
        } else {
            request.setAttribute("error", "Username or email already exists");
            // Giữ lại dữ liệu đã nhập để người dùng không phải nhập lại
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
