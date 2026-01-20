package com.clinicbooking.controller.auth;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.mapper.UserMapper;
import com.clinicbooking.model.entity.User;
import com.clinicbooking.service.AuthService;
import com.clinicbooking.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        phone = (phone == null) ? "" : phone.trim();
        password = (password == null) ? "" : password.trim();

        req.setAttribute("phone", phone);

        boolean hasError = false;

        if (phone.isBlank()) {
            req.setAttribute("phoneError", "Vui lòng nhập số điện thoại!");
            hasError = true;
        }

        if (password.isBlank()) {
            req.setAttribute("passwordError", "Vui lòng nhập mật khẩu!");
            hasError = true;
        }

        if (hasError) {
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
            return;
        }

        if (!ValidationUtil.isValidVietnamPhone(phone)) {
            req.setAttribute("error","Số điện thoại không hợp lệ! Ví dụ: 0901234567");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req,resp);
            return;
        }

        if (!ValidationUtil.isValidPassword(password)) {
            req.setAttribute("error","Mật khẩu không hợp lệ và không được để trống");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req,resp);
            return;
        }

        User user = authService.login(phone,password);

        if (user == null) {
            req.setAttribute("error","Sai số điện thoại hoặc mật khẩu!");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req,resp);
            return;
        }

        UserDto userDto = UserMapper.toUserDto(user);
        HttpSession session = req.getSession(true);
        session.setAttribute("user",userDto);

        if (userDto.getRole() != null && userDto.getRole().equalsIgnoreCase("ADMIN")) {
            resp.sendRedirect(req.getContextPath() + "/admin/appointments");
        } else {
            resp.sendRedirect(req.getContextPath() + "/doctors");
        }

    }
}
