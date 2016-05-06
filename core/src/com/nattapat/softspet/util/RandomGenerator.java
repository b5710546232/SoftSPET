package com.nattapat.softspet.util;

import java.util.Random;

/**
 * Created by nattapat on 5/2/2016 AD.
 */
public class RandomGenerator {
    private final int MAX = 3;
    private Random rnd;
    private int value;
    public static final RandomGenerator instance = new RandomGenerator();

    public RandomGenerator(){
        rnd = new Random();
    }

    public void random(){
        value = rnd.nextInt(MAX);
    }

    public int getValue(){
        return value;
    }
}
