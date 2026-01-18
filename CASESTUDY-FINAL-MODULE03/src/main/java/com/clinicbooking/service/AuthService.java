package com.clinicbooking.service;

import com.clinicbooking.dao.UserDao;
import com.clinicbooking.model.entity.User;
import com.clinicbooking.util.PasswordUtil;

public class AuthService {
    private final UserDao userDao = new UserDao();

    public User login(String phone, String password) {
        User user = userDao.findByPhone(phone);
        if (user == null) return null;

        if (PasswordUtil.verify(password,user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    public boolean register(String fullName, String phone, String password) {
        if (userDao.findByPhone(phone) != null) return false;
        String hash = PasswordUtil.hash(password);
        return userDao.register(fullName, phone, hash);
    }
}
