package com.clinicbooking.controller.appointment;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.Appointment;
import com.clinicbooking.model.entity.User;
import com.clinicbooking.service.AppointmentService;
import com.clinicbooking.service.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/appointments/create")
public class AppointmentCreateServlet extends HttpServlet {
    private final DoctorService doctorService = new DoctorService();
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("doctors",doctorService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/appointment/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        UserDto user = (UserDto) session.getAttribute("user");

        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        String dateStr = req.getParameter("date");
        String timeStr = req.getParameter("time");
        String note = req.getParameter("note");

        req.setAttribute("oldDate", dateStr);
        req.setAttribute("oldTime", timeStr);
        req.setAttribute("oldNote", note);
        req.setAttribute("oldDoctorId", doctorId);

        boolean hasError = false;

        if (dateStr == null || dateStr.trim().isEmpty()) {
            req.setAttribute("dateError", "Vui lòng chọn ngày khám!");
            hasError = true;
        }

        if (timeStr == null || timeStr.trim().isEmpty()) {
            req.setAttribute("timeError", "Vui lòng chọn giờ khám!");
            hasError = true;
        }

        if (hasError) {
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/create.jsp").forward(req, resp);
            return;
        }

        try {

            Appointment appointment = new Appointment();
            appointment.setPatientId(user.getId());
            appointment.setDoctorId(doctorId);
            appointment.setAppointmentDate(Date.valueOf(dateStr));
            appointment.setAppointmentTime(Time.valueOf(timeStr + ":00"));
            appointment.setNote(note);

            boolean ok = appointmentService.create(appointment);

            if (!ok) {
                req.setAttribute("error", "Slot này đã có người đặt. Chọn giờ khác nha!");
                req.setAttribute("doctors", doctorService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/appointment/create.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/appointments");

        } catch (ServletException e) {
            e.printStackTrace();
            req.setAttribute("error", "Dữ liệu không hợp lệ! Vui lòng kiểm tra lại.");
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/create.jsp").forward(req, resp);
        }
    }
}
