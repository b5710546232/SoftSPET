package com.nattapat.softspet.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nattapat.softspet.SoftspetMain;
import com.nattapat.softspet.gameobjects.Bread;
import com.nattapat.softspet.gameobjects.CleanerWave;
import com.nattapat.softspet.gameobjects.GameObject;
import com.nattapat.softspet.gameobjects.Meat;
import com.nattapat.softspet.gameobjects.Pet;
import com.nattapat.softspet.gameobjects.Vaccine;
import com.nattapat.softspet.util.Assets;

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
    private Game game;

    private Array<GameObject> container ;

    public GameWorld(Game game){
        this.game = (SoftspetMain)game;
        init();
    }

    private void init() {
        initTamagotchi();
        initComponent();
    }

    private void initComponent() {
        light = Light.getInstance();
        vaccine = new Vaccine();
        meat = new Meat(10,360,10);
        bread = new Bread(10,360,5);
        cleanerWave = new CleanerWave();
        container = new Array<GameObject>();

        container.add(vaccine);
        container.add(meat);
        container.add(pet);
        container.add(bread);
        container.add(cleanerWave);
    }

    public void drawGameObject(SpriteBatch batch){
        for(GameObject g : container){
            g.render(batch);
        }
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

    public void resume(){
        pet.stateTime = 0;
        Assets.instance.init(new AssetManager());
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
            vaccine.inject(pet);

    }


    public boolean isActive(){
        return pet.isActive && !pet.sleeping;
    }

    public Pet getPet() {
        return pet;
    }

    public void playMiniGame() {
        if(isActive()){
            game.setScreen(((SoftspetMain) game).getMiniGameScreen());
        }

    }

    public void selectFood() {
        if(isActive()) {
            game.setScreen(((SoftspetMain) game).getFoodScreen());
        }
    }
}
