package com.clinicbooking.service;

import com.clinicbooking.dao.AppointmentDao;
import com.clinicbooking.dto.AppointmentDto;
import com.clinicbooking.model.entity.Appointment;

import java.util.List;

public class AppointmentService {
    private final AppointmentDao appointmentDao = new AppointmentDao();

    public boolean create(Appointment appointment) {
        return appointmentDao.create(appointment);
    }

    public List<AppointmentDto> findByPatient(int patientId) {
        return appointmentDao.findByPatientId(patientId);
    }

    public List<AppointmentDto> findAllDto() {
        return appointmentDao.findAllDto();
    }

    public boolean cancel(int appointmentId, int patientId) {
        return appointmentDao.cancel(appointmentId,patientId);
    }
}
