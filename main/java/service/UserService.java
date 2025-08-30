package service;

import dao.UserDao;
import model.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserService {
    private UserDao userDAO;

    public UserService() {
        this.userDAO = new UserDao();
    }
    
    // Hàm để mã hóa mật khẩu bằng SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean authenticateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            // Xử lý mật khẩu an toàn
            String dbPassword = user.getPassword();
            if (dbPassword != null && password != null) {
                // Kiểm tra nếu mật khẩu đã được hash
                if (dbPassword.length() == 44) { // Độ dài của một chuỗi SHA-256 sau khi mã hóa Base64
                    return dbPassword.equals(hashPassword(password));
                } else {
                    // Nếu mật khẩu chưa hash (legacy), kiểm tra bình thường
                    return password.equals(dbPassword);
                }
            }
        }
        
        return false;
    }

    public boolean registerUser(String username, String email, String password) {
        // Kiểm tra hợp lệ
        if (username == null || username.trim().isEmpty() || 
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra username và email đã tồn tại hay chưa
        if (userDAO.checkUserExists(username)) {
            return false;
        }

        if (userDAO.checkEmailExists(email)) {
            return false;
        }

        // Hash mật khẩu trước khi lưu
        String hashedPassword = hashPassword(password.trim());
        if (hashedPassword == null) {
            return false;
        }
        
        User newUser = new User(username.trim(), email.trim(), hashedPassword);

        return userDAO.insertUser(newUser);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
