package com.nattapat.softspet.gameworld;

import com.nattapat.softspet.gameobjects.Light;
import com.nattapat.softspet.gameobjects.Pet;
import com.nattapat.softspet.stathandler.StatHandler;

/**
 * Created by nattapat on 4/6/2016 AD.
 */
public class GameWorld {
    private static final String TAG = GameWorld.class.getName();

    private Pet pet;
    private Light light;
    private StatHandler statHandler;

    public GameWorld(){
        init();
    }

    private void init() {
        initTamagotchi();
        light = new Light();
    }

    private void initTamagotchi() {
        pet = Pet.getInstance();
        statHandler = StatHandler.getInstance(pet);
    }



    public void update(float delta){
    }

    public void switchLight(){
        if(!light.isActive()) pet.sleep();
        else pet.wake();

    }
    public Pet getPet() {
        return pet;
    }

    public Light getLight() {
        return light;
    }
}
