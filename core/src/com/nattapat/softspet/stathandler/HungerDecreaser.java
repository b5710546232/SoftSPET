package com.nattapat.softspet.stathandler;

import com.badlogic.gdx.utils.Timer;
import com.nattapat.softspet.gameobjects.Pet;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class HungerDecreaser extends Timer.Task {
    private Pet pet;

    public HungerDecreaser(Pet tmg){
        pet = tmg;
    }
    @Override
    public void run() {
        pet.reduceHunger();
    }
}
