package com.tcomplex.dao;

import com.tcomplex.model.RentalSpace;
import com.tcomplex.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalSpaceDAO {
    public boolean existsById(String spaceId) {
        String sql = "SELECT space_id FROM rental_space WHERE space_id = ?";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, spaceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(RentalSpace space) {
        String sql = "INSERT INTO rental_space(space_id, area, status, floor, type, description, price, start_date, end_date)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setString(1,space.getSpaceId());
            preparedStatement.setDouble(2,space.getArea());
            preparedStatement.setString(3,space.getStatus());
            preparedStatement.setInt(4,space.getFloor());
            preparedStatement.setString(5, space.getType());
            preparedStatement.setString(6,space.getDescription());
            preparedStatement.setLong(7,space.getPrice());
            preparedStatement.setDate(8,space.getStartDate());
            preparedStatement.setDate(9,space.getEndDate());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<RentalSpace> findAllOrderByAreaAsc() {
        List<RentalSpace> list = new ArrayList<>();

        String sql = "SELECT * FROM rental_space ORDER BY area ASC";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(map(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<RentalSpace> search(String type, Long price, Integer floor) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM rental_space WHERE 1=1 ");

        if (type != null && !type.isBlank()) {
            sql.append(" AND type = ? ");
            params.add(type);
        }

        if (price != null) {
            sql.append(" AND price = ? ");
            params.add(price);
        }

        if (floor != null ) {
            sql.append(" AND floor = ? ");
            params.add(floor);
        }

        sql.append(" ORDER BY area ASC");

        List<RentalSpace> list = new ArrayList<>();

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())
                ) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i+1,params.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(map(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean deleteById(String spaceId) {
        String sql = "DELETE FROM rental_space WHERE space_id = ?";

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setString(1,spaceId);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RentalSpace map(ResultSet rs) throws SQLException {
        RentalSpace s = new RentalSpace();
        s.setSpaceId(rs.getString("space_id"));
        s.setArea(rs.getDouble("area"));
        s.setStatus(rs.getString("status"));
        s.setFloor(rs.getInt("floor"));
        s.setType(rs.getString("type"));
        s.setDescription(rs.getString("description"));
        s.setPrice(rs.getLong("price"));
        s.setStartDate(rs.getDate("start_date"));
        s.setEndDate(rs.getDate("end_date"));
        return s;
    }
}
