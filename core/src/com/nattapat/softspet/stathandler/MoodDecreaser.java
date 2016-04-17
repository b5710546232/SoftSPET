package com.nattapat.softspet.stathandler;

import com.badlogic.gdx.utils.Timer;
import com.nattapat.softspet.gameobjects.Pet;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class MoodDecreaser extends Timer.Task{
    private Pet pet;
    public MoodDecreaser(Pet p){
        pet = p;

    }
    @Override
    public void run() {
        pet.reduceMood();
    }
}
