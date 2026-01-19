package com.clinicbooking.service;

import com.clinicbooking.dao.UserDao;
import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.User;
import com.clinicbooking.util.PasswordUtil;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public List<UserDto> findAllPatients() {
        return userDao.findAllPatients();
    }

    public boolean changePassword(int userId, String currentPassword, String newPassword) {
        User user = userDao.findById(userId);

        if (user == null) return false;

        if (!PasswordUtil.verify(currentPassword, user.getPasswordHash())) {
            return false;
        }

        String newHash = PasswordUtil.hash(newPassword);

        return userDao.updatePasswordHash(userId,newHash);
    }
}
