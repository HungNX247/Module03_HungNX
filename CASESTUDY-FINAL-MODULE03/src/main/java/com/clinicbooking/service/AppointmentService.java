package com.clinicbooking.service;

import com.clinicbooking.dao.AppointmentDao;
import com.clinicbooking.dto.AppointmentDto;
import com.clinicbooking.model.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Appointment findByIdAndPatientId(int id, int patientId) {
        return appointmentDao.findByIdAndPatientId(id, patientId);
    }

    public boolean isDoctorBusy(int doctorId, LocalDate date, LocalTime time, int excludeId) {
        return appointmentDao.existsDoctorBookedSlot(doctorId, date, time, excludeId);
    }

    public boolean updateByPatient(int id, int patientId, int doctorId,
                                   LocalDate date, LocalTime time, String note) {
        return appointmentDao.updateByPatient(id, patientId, doctorId, date, time, note);
    }

}
