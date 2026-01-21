package com.clinicbooking.controller.appointment;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.Appointment;
import com.clinicbooking.service.AppointmentService;
import com.clinicbooking.service.DoctorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@WebServlet("/appointments/edit")
public class AppointmentEditServlet extends HttpServlet {

    private final AppointmentService appointmentService = new AppointmentService();
    private final DoctorService doctorService = new DoctorService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserDto currentUser = (session == null) ? null : (UserDto) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));


        Appointment appointment = appointmentService.findByIdAndPatientId(id, currentUser.getId());
        if (appointment == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        if (!"BOOKED".equalsIgnoreCase(appointment.getStatus())) {
            req.setAttribute("error", "Chỉ lịch ở trạng thái BOOKED mới được sửa.");
        }

        req.setAttribute("appointment", appointment);
        req.setAttribute("doctors", doctorService.findAll());

        req.getRequestDispatcher("/WEB-INF/views/appointment/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        UserDto currentUser = (session == null) ? null : (UserDto) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        LocalDate appointmentDate = LocalDate.parse(req.getParameter("appointmentDate"));
        LocalTime appointmentTime = LocalTime.parse(req.getParameter("appointmentTime"));
        String note = req.getParameter("note");
        note = (note == null) ? "" : note.trim();

        Appointment old = appointmentService.findByIdAndPatientId(id, currentUser.getId());
        if (old == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        if (!"BOOKED".equalsIgnoreCase(old.getStatus())) {
            req.setAttribute("error", "Chỉ lịch ở trạng thái BOOKED mới được sửa.");
            req.setAttribute("appointment", old);
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/edit.jsp").forward(req, resp);
            return;
        }


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oldApptTime = LocalDateTime.of(old.getAppointmentDate().toLocalDate(), old.getAppointmentTime().toLocalTime());
        long minutes = Duration.between(now, oldApptTime).toMinutes();

        if (minutes < 60) {
            req.setAttribute("error", "Bạn chỉ có thể sửa lịch trước giờ khám ít nhất 60 phút.");

            req.setAttribute("appointment", old);
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/edit.jsp").forward(req, resp);
            return;
        }


        boolean busy = appointmentService.isDoctorBusy(doctorId, appointmentDate, appointmentTime, id);
        if (busy) {
            req.setAttribute("error", "Bác sĩ đã có lịch vào khung giờ này. Vui lòng chọn giờ khác.");

            old.setDoctorId(doctorId);
            old.setAppointmentDate(Date.valueOf(appointmentDate));
            old.setAppointmentTime(Time.valueOf(appointmentTime));
            old.setNote(note);

            req.setAttribute("appointment", old);
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/edit.jsp").forward(req, resp);
            return;
        }


        boolean ok = appointmentService.updateByPatient(
                id,
                currentUser.getId(),
                doctorId,
                appointmentDate,
                appointmentTime,
                note
        );

        if (!ok) {
            req.setAttribute("error", "Cập nhật thất bại (có thể trùng lịch).");

            old.setDoctorId(doctorId);
            old.setAppointmentDate(Date.valueOf(appointmentDate));
            old.setAppointmentTime(Time.valueOf(appointmentTime));
            old.setNote(note);

            req.setAttribute("appointment", old);
            req.setAttribute("doctors", doctorService.findAll());
            req.getRequestDispatcher("/WEB-INF/views/appointment/edit.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/appointments?success=updated");
    }
}
