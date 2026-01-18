package com.clinicbooking.service;

import com.clinicbooking.dao.AppointmentDao;
import com.clinicbooking.model.entity.Appointment;

import java.util.List;

public class AppointmentService {
    private final AppointmentDao appointmentDao = new AppointmentDao();

    public boolean create(Appointment appointment) {
        return appointmentDao.create(appointment);
    }

    public List<Appointment> findByPatient(int patientId) {
        return appointmentDao.findByPatientId(patientId);
    }

    public boolean cancel(int appointmentId, int patientId) {
        return appointmentDao.cancel(appointmentId,patientId);
    }
}
