package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class Pet {
    private static final String TAG = Pet.class.getName();

    private static Pet instance;


    private int mood;
    private int health;
    private int hunger;
    private int stamina;

    private Vector2 position;

    private boolean isSick;
    private boolean isSleeping;


    private final int STAT_MAX = 100;
    private final int STAT_MIN = 0;
    private static int counter = 0;
    private static int counter2 = 0;


    private Pet() {
        init();
    }

    public static Pet getInstance() {
        if (instance == null) instance = new Pet();
        return instance;
    }

    private void init() {
        mood = 0;
        hunger = 100;
        stamina = 5;
        isSick = false;
        isSleeping = false;
        position = new Vector2(Constants.VIEWPORT_WIDTH / 2 - Assets.texutreArray_pets.get(0).getRegionWidth() / 2,
                Constants.VIEWPORT_HEIGHT / 2 - Assets.texutreArray_pets.get(0).getRegionHeight() / 1.5f);
    }


    public void eat(int point) {
        if(hunger>=STAT_MAX){

            log("pet full");
            return;
        }
        hunger = computeStat(hunger,point);
        log("hunger " + hunger);
        log("pet eat");
    }

    public void takeMedicine() {
        if (isSick) {
            health = computeStat(health,10);
            isSick = false;
            log("pet take medicine");
        } else {
            log("pet is not sick");
        }
    }

    public void sleep() {
        isSleeping = true;
        int value = MathUtils.random(15, 20);
        stamina = computeStat(stamina, value);
    }

    public void takeShower() {
        health = computeStat(health,10);
        mood = computeStat(mood,5);
        log("cleaner");
    }

    public void reduceHunger() {
        int value = 0;
        if(isSleeping)
            value = MathUtils.random(1, 5);
        else
            value = MathUtils.random(3, 10);
        hunger = computeStat(hunger, -value);
    }

    public void reduceHealth() {
        int value = MathUtils.random(3, 8);
        health = computeStat(health, -value);
        if (health < STAT_MAX / 2) isSick = true;
    }

    public void reduceMood() {
        if (health > STAT_MAX / 2.0f || hunger > STAT_MAX) return;
        int value = MathUtils.random(5, 10);
        mood = computeStat(mood, -value);
    }

    public void reduceStamina() {
        int value = MathUtils.random(5, 15);
        stamina = computeStat(stamina, -value);
    }

    private int computeStat(int stat, int value) {
        stat += value;
        if (stat >= STAT_MAX) stat = STAT_MAX;
        else if (stat <= STAT_MIN) stat = STAT_MIN;
        return stat;
    }

    public void happy() {
        int value = MathUtils.random(5, 15);
        mood = computeStat(mood,15);
    }


    public Vector2 getPosition() {
        return position;
    }

    public void log(String s) {
        Gdx.app.error(TAG, s);
    }

    public void wake() {
        isSleeping = false;
    }
}
