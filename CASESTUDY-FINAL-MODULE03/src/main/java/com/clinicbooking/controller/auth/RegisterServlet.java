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
        String confirmPassword = req.getParameter("confirmPassword");

        fullName = (fullName == null) ? "" : fullName.trim();
        phone = (phone == null) ? "" : phone.trim();
        password = (password == null) ? "" : password.trim();
        confirmPassword = (confirmPassword == null) ? "" : confirmPassword.trim();

        req.setAttribute("fullName", fullName);
        req.setAttribute("phone", phone);

        boolean hasError = false;

        if (fullName.isBlank()) {
            req.setAttribute("fullNameError", "Vui lòng nhập họ tên!");
            hasError = true;
        }
        if (phone.isBlank()) {
            req.setAttribute("phoneError", "Vui lòng nhập số điện thoại!");
            hasError = true;
        }
        if (password.isBlank()) {
            req.setAttribute("passwordError", "Vui lòng nhập mật khẩu!");
            hasError = true;
        }
        if (confirmPassword.isBlank()) {
            req.setAttribute("confirmPasswordError", "Vui lòng nhập lại mật khẩu!");
            hasError = true;
        }

        if (hasError) {
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu nhập lại không khớp!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
            return;
        }

        if (!ValidationUtil.isValidName(fullName)) {
            req.setAttribute("error","Họ tên phải >= 2 ký tự!");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        if (ValidationUtil.isValidVietnamPhone(phone)) {
            req.setAttribute("error","Số điện thoại không hợp lệ! Ví dụ: 0901234567");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req,resp);
            return;
        }

        if (ValidationUtil.isValidPassword(password)) {
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
