package com.itheo.r3pi.rest.assured.itest.util;

import java.util.Random;

/**
 * Created by theo on 30.03.17.
 */
public class RandomIntegerGenerator {

    public static int getRandomIntegerInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
