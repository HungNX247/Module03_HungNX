package com.clinicbooking.dao;

import com.clinicbooking.config.DBConnection;
import com.clinicbooking.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)

        ) {
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setRole(resultSet.getString("role"));
                return user;

            }
        } catch (Exception e) {
            throw new RuntimeException("UserDao.findByPhone error",e);
        }

        return null;
    }

    public boolean register(String fullName, String phone, String passwordHash) {
        String sql = "INSERT INTO users(full_name, phone, password_hash,role) VALUES (?,?,?, 'PATIENT')";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setString(1,fullName);
            preparedStatement.setString(2,phone);
            preparedStatement.setString(3,passwordHash);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }

    }
}
