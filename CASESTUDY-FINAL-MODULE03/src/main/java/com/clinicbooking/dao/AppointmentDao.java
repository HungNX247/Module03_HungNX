package com.clinicbooking.dao;

import com.clinicbooking.config.DBConnection;
import com.clinicbooking.dto.AppointmentDto;
import com.clinicbooking.model.entity.Appointment;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    public boolean create(Appointment appointment) {
        String sql = """
                INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time, status, note)
                VALUES (?, ?, ?, ?, 'BOOKED', ?)""";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)
                ) {
            preparedStatement.setInt(1,appointment.getPatientId());
            preparedStatement.setInt(2,appointment.getDoctorId());
            preparedStatement.setDate(3,appointment.getAppointmentDate());
            preparedStatement.setTime(4,appointment.getAppointmentTime());
            preparedStatement.setString(5,appointment.getNote());

            int affected = preparedStatement.executeUpdate();
            if (affected == 0) return false;

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    appointment.setId(resultSet.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                return false;
            }
            throw new RuntimeException("AppointmentDao.create error",e);
        }
    }

    public List<AppointmentDto> findAllDto() {
        List<AppointmentDto> list = new ArrayList<>();
        String sql = """
                SELECT a.id,
                       a.patient_id,
                       u.full_name AS patient_name,
                       u.phone AS patient_phone,
                       a.doctor_id,
                       d.full_name AS doctor_name,
                       s.name AS specialty_name,
                       a.appointment_date,
                       a.appointment_time,
                       a.status,
                       a.note
                FROM appointments a
                JOIN users u ON a.patient_id = u.id
                JOIN doctors d ON a.doctor_id = d.id
                JOIN specialties s ON d.specialty_id = s.id
                ORDER BY a.appointment_date DESC, a.appointment_time DESC""";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {
            while (resultSet.next()) {
                AppointmentDto dto = new AppointmentDto();

                dto.setId(resultSet.getInt("id"));

                dto.setPatientId(resultSet.getInt("patient_id"));
                dto.setPatientName(resultSet.getString("patient_name"));
                dto.setPatientPhone(resultSet.getString("patient_phone"));

                dto.setDoctorId(resultSet.getInt("doctor_id"));
                dto.setDoctorName(resultSet.getString("doctor_name"));
                dto.setSpecialtyName(resultSet.getString("specialty_name"));

                dto.setAppointmentDate(resultSet.getDate("appointment_date"));
                dto.setAppointmentTime(resultSet.getTime("appointment_time"));
                dto.setStatus(resultSet.getString("status"));
                dto.setNote(resultSet.getString("note"));

                list.add(dto);

            }
        } catch (Exception e) {
            throw new RuntimeException("AppointmentDao.findAllDto error", e);
        }
        return list;
    }

    public List<AppointmentDto> findByPatientId(int patientId) {
        List<AppointmentDto> list = new ArrayList<>();

        String sql = """
                SELECT a.id,
                    a.patient_id,
                    a.doctor_id,
                    d.full_name AS doctor_name,
                    s.name AS specialty_name,
                    a.appointment_date,
                    a.appointment_time,
                    a.status,
                    a.note
             FROM appointments a
             JOIN doctors d ON a.doctor_id = d.id
             JOIN specialties s ON d.specialty_id = s.id
             WHERE a.patient_id = ?
             ORDER BY a.appointment_date DESC, a.appointment_time DESC""";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1,patientId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(resultSet.getInt("id"));
                    appointmentDto.setPatientId(resultSet.getInt("patient_id"));
                    appointmentDto.setDoctorId(resultSet.getInt("doctor_id"));
                    appointmentDto.setDoctorName(resultSet.getString("doctor_name"));
                    appointmentDto.setSpecialtyName(resultSet.getString("specialty_name"));
                    appointmentDto.setAppointmentDate(resultSet.getDate("appointment_date"));
                    appointmentDto.setAppointmentTime(resultSet.getTime("appointment_time"));
                    appointmentDto.setStatus(resultSet.getString("status"));
                    appointmentDto.setNote(resultSet.getString("note"));
                    list.add(appointmentDto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("AppointmentDao.findByPatientId error",e);
        }
        return list;
    }

    public boolean cancel(int appointmentId, int patientId) {
        String sql = """
                UPDATE appointments
                SET status = 'CANCELED'
                WHERE id = ? AND patient_id = ? AND status = 'BOOKED'""";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1,appointmentId);
            preparedStatement.setInt(2,patientId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("AppointmentDao.cancel error",e);
        }

    }
}
