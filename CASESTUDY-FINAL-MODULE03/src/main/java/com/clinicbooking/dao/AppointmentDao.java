package com.clinicbooking.dao;

import com.clinicbooking.config.DBConnection;
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
            throw new RuntimeException("AppointmentDao.create error",e);
        }
    }

    public List<Appointment> findByPatientId(int patientId) {
        List<Appointment> list = new ArrayList<>();

        String sql = """
                SELECT id, patient_id, doctor_id, appointment_date, appointment_time, status, note
                FROM appointments
                WHERE patient_id = ?
                ORDER BY appointment_date DESC, appointment_time DESC""";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1,patientId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setId(resultSet.getInt("id"));
                    appointment.setPatientId(resultSet.getInt("patient_id"));
                    appointment.setDoctorId(resultSet.getInt("doctor_id"));
                    appointment.setAppointmentDate(resultSet.getDate("appointment_date"));
                    appointment.setAppointmentTime(resultSet.getTime("appointment_time"));
                    appointment.setStatus(resultSet.getString("status"));
                    appointment.setNote(resultSet.getString("note"));
                    list.add(appointment);
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
