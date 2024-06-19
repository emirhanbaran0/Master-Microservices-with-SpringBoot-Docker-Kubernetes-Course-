package com.emirhanbaran.accounts.utils;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static long generateRandomLong(int length) {
        if (length <= 0 || length > 18) {
            throw new IllegalArgumentException("Length must be between 1 and 18");
        }

        StringBuilder sb = new StringBuilder(length);
        // Ensure the first digit is not zero
        sb.append(random.nextInt(9) + 1);

        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return Long.parseLong(sb.toString());
    }
}
