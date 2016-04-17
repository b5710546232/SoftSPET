package com.nattapat.softspet.stathandler;

import com.badlogic.gdx.utils.Timer;
import com.nattapat.softspet.gameobjects.Pet;

/**
 * Created by nattapat on 4/15/2016 AD.
 */
public class StaminaDecreaser extends Timer.Task {
    private Pet pet;

    public StaminaDecreaser(Pet p) {
        pet = p;
    }

    @Override
    public void run() {
        pet.reduceStamina();
    }
}
