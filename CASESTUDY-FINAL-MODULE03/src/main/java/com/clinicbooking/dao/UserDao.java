package com.clinicbooking.dao;

import com.clinicbooking.config.DBConnection;
import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public  List<UserDto> findAllPatients() {
        List<UserDto> list = new ArrayList<>();

        String sql = """
                SELECT id, full_name, phone, role
                FROM users
                WHERE role = 'PATIENT'
                ORDER BY id DESC""";

        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ) {
            while (rs.next()) {
                UserDto userDto = new UserDto();
                userDto.setId(rs.getInt("id"));
                userDto.setFullName(rs.getString("full_name"));
                userDto.setPhone(rs.getString("phone"));
                userDto.setRole(rs.getString("role"));
                list.add(userDto);
            }
        } catch (Exception e) {
            throw new RuntimeException("UserDao.findAllPatients error", e);
        }
        return list;
    }

    public User findById(int id) {
    String sql = "SELECT id, full_name, phone, password_hash, role, created_at FROM users WHERE id = ?";
    try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
        preparedStatement.setInt(1, id);

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setRole(resultSet.getString("role"));

                return user;
            }
        }

    } catch (Exception e) {
        throw new RuntimeException("UserDao.findById error", e);
    }
        return null;
    }

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

    public boolean updatePasswordHash(int userId, String newHash) {
        String sql = "UPDATE users SET password_hash = ? WHERE id = ?";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
            preparedStatement.setString(1,newHash);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("UserDao.updatePasswordHash error", e);
        }
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
