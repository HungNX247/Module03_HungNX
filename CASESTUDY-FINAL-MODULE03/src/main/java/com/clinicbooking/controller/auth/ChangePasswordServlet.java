package com.clinicbooking.controller.auth;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.service.UserService;
import com.clinicbooking.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/change_password.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        currentPassword = (currentPassword == null) ? "" : currentPassword.trim();
        newPassword = (newPassword == null) ? "" : newPassword.trim();
        confirmPassword = (confirmPassword == null) ? "" : confirmPassword.trim();

        boolean hasError = false;

        if (currentPassword.isBlank()) {
            req.setAttribute("currentPasswordError", "Vui lòng nhập mật khẩu hiện tại!");
            hasError = true;
        }

        if (newPassword.isBlank()) {
            req.setAttribute("newPasswordError", "Vui lòng nhập mật khẩu mới!");
            hasError = true;
        }

        if (confirmPassword.isBlank()) {
            req.setAttribute("confirmPasswordError", "Vui lòng nhập lại mật khẩu mới!");
            hasError = true;
        }

        if (hasError) {
            doGet(req, resp);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu mới nhập lại không khớp!");
            doGet(req,resp);
            return;
        }

        if (ValidationUtil.isValidPassword(newPassword)) {
            req.setAttribute("error", "Mật khẩu mới phải từ 6 ký tự trở lên!");
            doGet(req,resp);
            return;
        }

        if (newPassword.equals(currentPassword)) {
            req.setAttribute("error", "Mật khẩu mới không được trùng với mật khẩu hiện tại!");
            doGet(req,resp);
            return;
        }

        boolean ok = userService.changePassword(user.getId(), currentPassword, newPassword);

        if (!ok) {
            req.setAttribute("error", "Mật khẩu hiện tại không đúng!");
            doGet(req, resp);
            return;
        }

        session.invalidate();

        HttpSession newSession = req.getSession(true);
        newSession.setAttribute("success", "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
