package com.clinicbooking.controller.admin;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.service.AppointmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/appointments")
public class AdminAppointmentListServlet extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        UserDto user = (UserDto) session.getAttribute("user");
        if (user.getRole() == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/doctors");
            return;
        }

        String success = (String) session.getAttribute("success");
        if (success != null) {
            request.setAttribute("success", success);
            session.removeAttribute("success");
        }

        request.setAttribute("appointments",appointmentService.findAllDto());
        request.getRequestDispatcher("/WEB-INF/views/admin/appointments.jsp").forward(request, response);
    }
}
