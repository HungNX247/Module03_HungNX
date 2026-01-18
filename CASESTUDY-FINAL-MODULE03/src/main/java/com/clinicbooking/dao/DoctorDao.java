package com.clinicbooking.dao;

import com.clinicbooking.config.DBConnection;
import com.clinicbooking.dto.DoctorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    public List<DoctorDto> findAllDto() {
        List<DoctorDto> list = new ArrayList<>();
        String sql = """
                SELECT d.id, d.full_name, d.specialty_id, s.name AS specialty_name, d.phone, d.price
                FROM doctors d
                JOIN specialties s ON d.specialty_id = s.id
                ORDER BY d.id
                """;

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(resultSet.getInt("id"));
                doctorDto.setFullName(resultSet.getString("full_name"));
                doctorDto.setSpecialtyId(resultSet.getInt("specialty_id"));
                doctorDto.setPhone(resultSet.getString("phone"));
                doctorDto.setPrice(resultSet.getInt("price"));
                list.add(doctorDto);
            }
        } catch (Exception e) {
            throw new RuntimeException("DoctorDao.findAll error",e);
        }
        return list;
    }

    public DoctorDto findDtoById(int id) {
        String sql = """
                SELECT d.id, d.full_name, d.specialty_id, s.name AS specialty_name, d.phone, d.price
                FROM doctors d
                JOIN specialties s ON d.specialty_id = s.id
                WHERE d.id = ?
                """;
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(resultSet.getInt("id"));
                doctorDto.setFullName(resultSet.getString("full_name"));
                doctorDto.setSpecialtyId(resultSet.getInt("specialty_id"));
                doctorDto.setSpecialtyName(resultSet.getString("specialty_name"));
                doctorDto.setPhone(resultSet.getString("phone"));
                doctorDto.setPrice(resultSet.getInt("price"));
                return doctorDto;
            }
        } catch (Exception e) {
            throw new RuntimeException("DoctorDao.findById error",e);
        }
        return null;
    }
}
