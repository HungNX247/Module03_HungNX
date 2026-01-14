package com.codegym.controller;

import com.codegym.dao.UserDao;
import com.codegym.mapper.UserMapper;
import com.codegym.model.dto.UserDto;
import com.codegym.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "loginservlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDao.findByUsernameAndPassword(username,password);

        if (user != null) {
            UserDto dto = UserMapper.toDto(user);
            req.getSession().setAttribute("user", dto);
        }
    }
}
