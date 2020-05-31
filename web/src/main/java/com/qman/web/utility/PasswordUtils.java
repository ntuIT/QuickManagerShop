package com.qman.web.utility;

import com.qman.web.support.BCrypt;

public class PasswordUtils {

    public static String makePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(11));
    }

    public static Boolean checkPassword(String originalPw, String hashedPw) {
        return BCrypt.checkpw(originalPw, hashedPw);
    }
}