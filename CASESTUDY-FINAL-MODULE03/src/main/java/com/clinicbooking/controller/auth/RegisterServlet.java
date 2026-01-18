package com.clinicbooking.controller.auth;

import com.clinicbooking.service.AuthService;
import com.clinicbooking.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        if (fullName != null) {
            fullName = fullName.trim();
            if (fullName.isEmpty()) fullName = null;
        }
        req.setAttribute("fullName",fullName);

        if (phone != null) {
            phone = phone.trim();
            if (phone.isEmpty()) phone = null;
        }
        req.setAttribute("phone",phone);

        if (fullName == null || phone == null || password == null) {
            req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (!ValidationUtil.isValidName(fullName)) {
            req.setAttribute("error","Họ tên phải >= 2 ký tự!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        if (!ValidationUtil.isValidVietnamPhone(phone)) {
            req.setAttribute("error","SĐT không hợp lệ! Ví dụ: 0901234567");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        if (!ValidationUtil.isValidPassword(password)) {
            req.setAttribute("error","Mật khẩu tối thiểu 6 ký tự!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        boolean ok = authService.register(fullName,phone,password);

        if (!ok) {
            req.setAttribute("error", "Số điện thoại đã tồn tại!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("success","Đăng ký thành công! Mời bạn đăng nhập.");
        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
