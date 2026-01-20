package com.clinicbooking.util;


public class ValidationUtil {
    public static boolean isValidVietnamPhone(String phone) {
        if (phone == null) return true;
        return !phone.matches("^(0[3|5|7|8|9])[0-9]{8}$");
    }

    public static boolean isValidPassword(String password) {
        return password == null || password.length() < 6;
    }

    public static boolean isValidName(String fullname) {
        return fullname != null && fullname.trim().length() >= 2;
    }
}
