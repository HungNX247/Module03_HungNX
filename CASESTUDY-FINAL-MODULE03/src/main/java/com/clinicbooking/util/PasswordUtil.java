package com.clinicbooking.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hash(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(10));
    }

    public static boolean verify(String rawPassword, String hash) {
        return BCrypt.checkpw(rawPassword, hash);
    }


}
