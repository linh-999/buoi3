package controller;

import dao.UserDao;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/forget-password")
public class ForgetPasswordServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("forget-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("error", "Please enter your email.");
            req.getRequestDispatcher("forget-password.jsp").forward(req, resp);
            return;
        }
        User user = null;
        try {
            user = userDao.getUserByUsernameOrEmail(null, email.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            req.setAttribute("error", "Email not found.");
        } else {
            // Tạo mật khẩu mới ngẫu nhiên
            String newPassword = "new" + (int)(Math.random() * 1000000);
            // Cập nhật mật khẩu mới vào database (plaintext)
            userDao.updatePasswordByEmail(email.trim(), newPassword);
            req.setAttribute("success", "Your new password is: " + newPassword);
        }
        req.getRequestDispatcher("forget-password.jsp").forward(req, resp);
    }
}