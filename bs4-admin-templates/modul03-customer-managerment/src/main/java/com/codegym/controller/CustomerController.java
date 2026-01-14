package com.codegym.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "hello-servlet", urlPatterns = {"/", "/customers", "/customer/detail", "/customer/add",
        "/customer/edit", "/customer/remove" })
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", "Hello HungNX");
        req.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(req, resp);
    }
}
