package com.gmail.muhsener98.surveymanagementproject2.shared;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

public class AlphanumericStringGenerator {

    private static final String KEY = "qwertyuoplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM123456789";
    private static final Random RANDOM = new SecureRandom();
    private static final int DEFAULT_LENGTH = 30;


    /**
     * It generates an alphanumeric string.
     * If length < 0 , then it generates in DEFAULT LENGTH(30)
     * @param length - the length of generated string.
     * @return alphanumeric string in specified length
     */
    public static String generate(int length) {
        length = checkInput(length);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(KEY.charAt(RANDOM.nextInt(KEY.length())));
        }
        return sb.toString();
    }

    private static int checkInput(int length) {
        if (length < 0)
            return DEFAULT_LENGTH;
        return length;
    }
}
