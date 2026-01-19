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

@WebServlet("/appointments/cancel")
public class AppointmentCancelServlet extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserDto user = (UserDto) session.getAttribute("user");

        int id = Integer.parseInt(req.getParameter("id"));
        appointmentService.cancel(id,user.getId());

        resp.sendRedirect(req.getContextPath() + "/appointments");
    }
}
