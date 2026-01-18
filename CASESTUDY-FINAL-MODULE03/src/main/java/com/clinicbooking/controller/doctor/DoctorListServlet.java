package com.clinicbooking.controller.doctor;

import com.clinicbooking.service.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/doctors")
public class DoctorListServlet extends HttpServlet {
    private final DoctorService doctorService = new DoctorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("doctors",doctorService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/doctor/list.jsp").forward(req,resp);
    }
}
