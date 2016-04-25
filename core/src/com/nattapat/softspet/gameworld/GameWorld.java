package com.nattapat.softspet.gameworld;

import com.badlogic.gdx.Gdx;
import com.nattapat.softspet.gameobjects.Bread;
import com.nattapat.softspet.gameobjects.CleanerWave;
import com.nattapat.softspet.gameobjects.Meat;
import com.nattapat.softspet.gameobjects.Pet;
import com.nattapat.softspet.gameobjects.Vaccine;

/**
 * Created by nattapat on 4/6/2016 AD.
 */
public class GameWorld {
    private static final String TAG = GameWorld.class.getName();

    private Pet pet;
    private Light light;
    private Vaccine vaccine;
    private CleanerWave cleanerWave;
    private Bread bread;
    private Meat meat;

    public GameWorld(){
        init();
    }

    private void init() {
        initTamagotchi();
        initComponent();
    }

    private void initComponent() {
        light = new Light();
        vaccine = new Vaccine();
        meat = new Meat(10,360,10);
        bread = new Bread(10,360,5);
        cleanerWave = new CleanerWave();
    }

    private void initTamagotchi() {
        pet = Pet.getInstance();
    }

    public void update(float delta){
        pet.update(delta);
        vaccine.update(delta);
        meat.update(delta);
        bread.update(delta);
        cleanerWave.update(delta);
    }

    public void switchLight(){
        if(pet.isActive) {
            light.click();
            if (!light.isActive()) {
                pet.sleep();
                Gdx.app.error(TAG, "sleep");
            } else pet.wake();
        }

    }
    public Pet getPet() {
        return pet;
    }

    public Light getLight() {
        return light;
    }

    public void cleanPet(){
        if(!isActive())return;
        pet.takeShower();
        cleanerWave.clean();
    }

    public void giveMeatToPet(){
        pet.eat(meat.getHuggerPoint());
        meat.eatten();
    }

    public void giveBreadToPet(){
        pet.eat(bread.getHuggerPoint());
        bread.eatten();
    }

    public void injectVaccineToPet(){
        if(!isActive())return;
        if(pet.isSick()){
            vaccine.inject();
        }
        pet.takeMedicine();

    }
    public Meat getMeat() {
        return meat;
    }

    public boolean isActive(){
        return pet.isActive && !pet.sleeping;
    }
    public CleanerWave getCleanerWave() {
        return cleanerWave;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }
    public Bread getBread() {
        return bread;
    }
}
