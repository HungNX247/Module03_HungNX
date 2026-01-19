package com.clinicbooking.controller.admin;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.Appointment;
import com.clinicbooking.service.AppointmentService;
import com.clinicbooking.service.DoctorService;
import com.clinicbooking.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@WebServlet("/admin/appointments/create")
public class AdminAppointmentCreateServlet extends HttpServlet {

    private final DoctorService doctorService =  new DoctorService();
    private final UserService userService =  new UserService();
    private final AppointmentService appointmentService =  new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null ||session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserDto user = (UserDto) session.getAttribute("user");
        if (user.getRole() == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/doctors");
            return;
        }

        req.setAttribute("doctors", doctorService.findAll());
        req.setAttribute("patients", userService.findAllPatients());
        req.getRequestDispatcher("/WEB-INF/views/admin/appointments-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null ||session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserDto user = (UserDto) session.getAttribute("user");
        if (user.getRole() == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/doctors");
            return;
        }

        try {
            String patientIdStr = req.getParameter("patientId");
            String doctorIdStr = req.getParameter("doctorId");
            String dateStr =  req.getParameter("date");
            String timeStr = req.getParameter("time");
            String note = req.getParameter("note");

            if (patientIdStr == null || patientIdStr.isEmpty()
                    || doctorIdStr == null || doctorIdStr.isEmpty()
                    || dateStr == null || dateStr.isEmpty()
                    || timeStr == null || timeStr.isEmpty()) {
                req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin lịch hẹn!");
                req.setAttribute("doctors", doctorService.findAll());
                req.setAttribute("patients", userService.findAllPatients());
                req.getRequestDispatcher("/WEB-INF/views/admin/appointments-create.jsp").forward(req,resp);
                return;
            }

            int patientId = Integer.parseInt(patientIdStr);
            int doctorId = Integer.parseInt(doctorIdStr);

            Appointment appointment = new Appointment();
            appointment.setPatientId(patientId);
            appointment.setDoctorId(doctorId);
            appointment.setAppointmentDate(Date.valueOf(dateStr));
            if (timeStr.length() == 5) {
                timeStr = timeStr + ":00";
            }
            LocalTime localTime = LocalTime.parse(timeStr);
            appointment.setAppointmentTime(Time.valueOf(localTime));
            appointment.setNote(note);

            boolean ok = appointmentService.create(appointment);
            if (!ok) {
                req.setAttribute("error", "Slot này đã có người đặt. Vui lòng chọn giờ khác!");
                req.setAttribute("doctors", doctorService.findAll());
                req.setAttribute("patients", userService.findAllPatients());
                req.getRequestDispatcher("/WEB-INF/views/admin/appointments-create.jsp").forward(req,resp);
                return;
            }

            session.setAttribute("success", "Tạo lịch hẹn thành công!");
            resp.sendRedirect(req.getContextPath() + "/admin/appointments");
        } catch (Exception e) {
            e.printStackTrace();

            req.setAttribute("error","Lỗi: " + e.getMessage());
            req.setAttribute("doctors", doctorService.findAll());
            req.setAttribute("patients", userService.findAllPatients());
            req.getRequestDispatcher("/WEB-INF/views/admin/appointments-create.jsp").forward(req,resp);
        }
    }
}
