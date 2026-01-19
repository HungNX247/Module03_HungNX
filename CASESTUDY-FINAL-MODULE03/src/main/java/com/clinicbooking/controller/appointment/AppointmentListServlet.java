package com.clinicbooking.controller.appointment;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.User;
import com.clinicbooking.service.AppointmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/appointments")
public class AppointmentListServlet extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDto user = (UserDto) session.getAttribute("user");

        req.setAttribute("appointments",appointmentService.findByPatient(user.getId()));
        req.getRequestDispatcher("/WEB-INF/views/appointment/list.jsp").forward(req,resp);
    }
}
