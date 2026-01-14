package com.codegym.model.dao;

import com.codegym.connection.JdbcConnection;
import com.codegym.model.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private List<Customer> customers = null;

    public List<Customer> getAll() {
        customers = new ArrayList<>();

        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM customers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPosition(resultSet.getString("position"));
                customer.setOffice(resultSet.getString("office"));
                customer.setAge(resultSet.getInt("age"));
                customer.setStartDate(resultSet.getDate("start_date"));
                customer.setSalary(resultSet.getDouble("salary"));
                customers.add(customer);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Customer get(int id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM customers WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPosition(resultSet.getString("position"));
                customer.setOffice(resultSet.getString("office"));
                customer.setAge(resultSet.getInt("age"));
                customer.setStartDate(resultSet.getDate("start_date"));
                customer.setSalary(resultSet.getDouble("salary"));
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Customer();
    }

    public List<Customer> fetch(String searchingName) {
        List<Customer> searchedResult = null;
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "SELECT * FROM customers WHERE name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, searchingName);
            ResultSet resultSet = preparedStatement.executeQuery();

            searchedResult = new ArrayList<>();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setPosition(resultSet.getString("position"));
                customer.setOffice(resultSet.getString("office"));
                customer.setAge(resultSet.getInt("age"));
                customer.setStartDate(resultSet.getDate("start_date"));
                customer.setSalary(resultSet.getDouble("salary"));
                searchedResult.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchedResult;
    }

    public void insert(Customer customer) {
        try {
            if(customer.getId() != null) {
                System.out.println("Customer is existed.");
                return;
            }

            Connection connection = JdbcConnection.getConnection();
            String query = "INSERT INTO customers(name, position, office, age, start_date, salary) " +
                            "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPosition());
            preparedStatement.setString(3, customer.getOffice());
            preparedStatement.setInt(4, customer.getAge());

            java.util.Date utilStartDate = customer.getStartDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

            preparedStatement.setDate(5, sqlStartDate);
            preparedStatement.setDouble(6, customer.getSalary());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Added customer successfully.");
            } else {
                System.out.println("Failed to insert customer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "UPDATE customers " +
                           "SET name = ?, position = ?, office = ?, age = ?, start_date = ?, salary = ? " +
                           "WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPosition());
            preparedStatement.setString(3, customer.getOffice());
            preparedStatement.setInt(4, customer.getAge());

            java.util.Date utilStartDate = customer.getStartDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

            preparedStatement.setDate(5, sqlStartDate);
            preparedStatement.setDouble(6, customer.getSalary());
            preparedStatement.setInt(7, customer.getId());

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Edited customer successfully.");
            } else {
                System.out.println("Failed to edit customer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            Connection connection = JdbcConnection.getConnection();
            String query = "DELETE FROM customers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() > 0) {
                System.out.println("Removed customer successfully.");
            } else {
                System.out.println("Failed to remove customer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
