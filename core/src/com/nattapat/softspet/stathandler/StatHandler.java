package com.nattapat.softspet.stathandler;

import com.badlogic.gdx.utils.Timer;
import com.nattapat.softspet.gameobjects.Pet;

/**
 * Created by nattapat on 4/15/2016 AD.
 */
public class StatHandler {
    private Pet pet;
    private final float HUNGER_TIME = 3.0f;
    private final float STAMINA_TIME = 10.0f;
    private final float MOOD_TIME = 15.0f;
    private final float HEALTH_TIME = 8.0f;

    HungerDecreaser hungerDecreaser;
    HealthDecreaser healthDecreaser;
    MoodDecreaser moodDecreaser;
    StaminaDecreaser staminaDecreaser;
    private static StatHandler instance = null;

    private StatHandler(Pet p) {
        pet = p;
        init();
    }

    public static StatHandler getInstance(Pet p){
        if(instance == null) instance = new StatHandler(p);
        return  instance;
    }

    private void init() {
        hungerDecreaser = new HungerDecreaser(pet);
        healthDecreaser = new HealthDecreaser(pet);
        moodDecreaser = new com.nattapat.softspet.stathandler.MoodDecreaser(pet);
        staminaDecreaser = new StaminaDecreaser(pet);

        Timer.schedule(hungerDecreaser, 0, HUNGER_TIME);
        Timer.schedule(healthDecreaser, 0, HEALTH_TIME);
        Timer.schedule(moodDecreaser, 0, MOOD_TIME);
        Timer.schedule(staminaDecreaser, 0, STAMINA_TIME);
        Timer.instance().start();

    }
}
