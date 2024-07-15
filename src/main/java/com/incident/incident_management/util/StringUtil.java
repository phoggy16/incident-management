package com.incident.incident_management.util;

import java.util.Random;

public interface StringUtil {
    static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        return String.format("%05d", number);
    }
}
