package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Constant;
import utils.IOUtils;

@SuppressWarnings("serial")
// Đã vô hiệu hóa vì xung đột với ImageServlet
// @WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        File file = new File(Constant.DIR + "/" + fileName);
        resp.setContentType("image/jpeg");
        if (file.exists()) {
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
        }
}
}
