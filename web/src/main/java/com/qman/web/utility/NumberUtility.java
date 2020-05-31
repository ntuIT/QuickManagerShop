package com.qman.web.utility;

import java.util.Random;

public class NumberUtility {

    public static String getRandomSecretCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}
