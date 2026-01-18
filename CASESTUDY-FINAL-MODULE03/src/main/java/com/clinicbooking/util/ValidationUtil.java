package com.clinicbooking.util;


public class ValidationUtil {
    public static boolean isValidVietnamPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("^0\\d{9}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >=6;
    }

    public static boolean isValidName(String fullname) {
        return fullname != null && fullname.trim().length() >= 2;
    }
}
