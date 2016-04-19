package com.nattapat.softspet.gameworld;

import com.badlogic.gdx.Gdx;
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
        pet.update(delta);
    }

    public void switchLight(){
        light.click();
        if(!light.isActive()) {
            pet.sleep();
            Gdx.app.error(TAG,"sleep");
        }
        else pet.wake();

    }
    public Pet getPet() {
        return pet;
    }

    public Light getLight() {
        return light;
    }
}
