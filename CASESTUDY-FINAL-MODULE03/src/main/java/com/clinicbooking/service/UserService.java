package com.clinicbooking.service;

import com.clinicbooking.dao.UserDao;
import com.clinicbooking.dto.UserDto;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public List<UserDto> findAllPatients() {
        return userDao.findAllPatients();
    }
}
