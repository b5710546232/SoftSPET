package com.nattapat.softspet.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Timer;
import com.nattapat.softspet.SoftspetMain;
import com.nattapat.softspet.gameobjects.Bread;
import com.nattapat.softspet.gameobjects.CleanerWave;
import com.nattapat.softspet.gameobjects.CompositeGameObject;
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

    public CompositeGameObject getWorldContainer() {
        return worldContainer;
    }

    private CompositeGameObject worldContainer;
    private Bread bread;
    private Meat meat;
    private Game game;


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
        meat = new Meat(10,360,15);
        bread = new Bread(10,360,10);
        cleanerWave = new CleanerWave();
        worldContainer = new CompositeGameObject();
        worldContainer.add(vaccine);
        worldContainer.add(meat);
        worldContainer.add(pet);
        worldContainer.add(bread);
        worldContainer.add(cleanerWave);
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

    public  void showPetEmotion(){
        if(Pet.getInstance().showemotion || !isActive()) return;
        Pet.getInstance().showemotion = true;

        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {

                               Pet.getInstance().showemotion = false;

                           }
                       }
                , 3.0f        //    (delay)
                , 0   //    (seconds)
                , 0
        );
    }

    public void hideEmotion(){
        Pet.getInstance().showemotion = false;
    }

    public void switchLight(){
        if(pet.isActive && !pet.showemotion) {
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
        if(!pet.isFull)
        meat.eatten();
    }

    public void giveBreadToPet(){
        pet.eat(bread.getHuggerPoint());
        if(!pet.isFull)
        bread.eatten();
    }

    public void injectVaccineToPet(){
        if(!isActive())return;
            vaccine.inject(pet);

    }


    public boolean isActive(){
        return pet.isActive && !pet.sleeping &&!pet.showemotion;
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
