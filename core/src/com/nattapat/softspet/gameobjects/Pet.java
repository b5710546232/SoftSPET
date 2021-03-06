package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nattapat.softspet.gameworld.Light;
import com.nattapat.softspet.stathandler.StatHandler;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;
import com.nattapat.softspet.util.GamePreferences;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class Pet implements GameObject{
    private static final String TAG = Pet.class.getName();

    private static Pet instance;
    private StatHandler statHandler;
    public float stateTime;

    private int mood;
    private int health;
    private int hunger;
    private int stamina;

    private Vector2 shadowPosition;

    private boolean isSick;
    public boolean sleeping;
    public boolean showemotion;
    public boolean isFull;

    public boolean isActive;
    private final int STAT_MAX = 100;
    private final int STAT_MIN = 0;
    private static int counter = 0;
    private static int counter2 = 0;
    private static final float POSX = Constants.VIEWPORT_WIDTH / 2 - Assets.texutreArray_pets.get(0).getRegionWidth() / 2;
    private static final float POSY = Constants.VIEWPORT_HEIGHT / 2 - Assets.texutreArray_pets.get(0).getRegionHeight() / 1.5f;
    private Vector2 position;



    private final int EMOTION_HAPPY = 3;
    private final int EMOTION_NORMAL = 2;
    private final int EMOTION_SAD = 1;
    private final int EMOTION_VERYDSAD = 0;

    private int CURRENT_EMOTION;

    private Animation currentAnimation;

    private Pet()
    {
        init();
    }


    public static Pet getInstance() {
        if (instance == null) instance = new Pet();
        return instance;
    }

    private void init() {
        mood = 70;
        hunger = 100;
        stamina = 5;
        health = 40;
        statHandler = StatHandler.getInstance(this);

        stateTime = 0;
        showemotion = false;
        isSick = false;
        sleeping = false;
        isActive = true;
        setAnimation(Assets.pet_anim_idle);
        position = new Vector2(POSX, POSY);
        checkEmotion();

        shadowPosition = new Vector2(position.x,position.y - Assets.texutreArray_pets.get(0).getRegionHeight() / 2);
    }

    private void checkEmotion(){
        if(mood>=80){
            CURRENT_EMOTION = EMOTION_HAPPY;
        }
        else if(mood<80 && mood >= 50){
            CURRENT_EMOTION = EMOTION_NORMAL;
        }
        else if(mood<50 && mood >=20){
            CURRENT_EMOTION = EMOTION_SAD;
        }
        else{
            CURRENT_EMOTION = EMOTION_VERYDSAD;
        }
    }




    public void eat(int point) {
        if(sleeping)return;
        isActive = false;
        log("hunger " + hunger);
        if(hunger>=STAT_MAX){
            setAnimation(Assets.pet_anim_take_say_no);
            log("pet full");
            isFull = true;
        }
        else {
            hunger = computeStat(hunger,point);
            setAnimation(Assets.pet_anim_eat);
            log("pet eat");
        }
    }

    public void takeMedicine() {
        if(sleeping)return;
        isActive = false;
        if (isSick) {
            health = computeStat(health,10);
            if (health >= STAT_MAX / 2) isSick = false;
            log("pet take medicine");
            setAnimation(Assets.pet_anim_take_med);
        } else {
            setAnimation(Assets.pet_anim_take_say_no);
            log("pet is not sick");
        }
    }

    public void sleep() {
        sleeping = true;
        setAnimation(Assets.pet_anim_sleep);
        int value = MathUtils.random(15, 20);
        stamina = computeStat(stamina, value);
    }

    public void takeShower() {
        if(sleeping)return;
        isActive = false;
        health = computeStat(health,10);
        log("cleaner");
    }


    public void wake() {
        isActive = true;
        sleeping = false;
        setAnimation(Assets.pet_anim_idle);
    }



    public void reduceHunger() {
//        log("hunger"+counter++);
        int value = 0;
        if(sleeping)
            value = MathUtils.random(1, 5);
        else
            value = MathUtils.random(3, 10);
        hunger = computeStat(hunger, -value);
        if(hunger<STAT_MAX)isFull = false;
    }

    public void reduceHealth() {
        int value = MathUtils.random(3, 8);
        health = computeStat(health, -value);
        if (health < STAT_MAX / 2) isSick = true;
    }

    public void reduceMood() {
        if (health > STAT_MAX / 2.0f || hunger > STAT_MAX) return;
        int value = MathUtils.random(5, 10);
        if(hunger < STAT_MAX/2 || health < STAT_MAX /2 || stamina < STAT_MAX/2)
            value -= 2;
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
        int value = MathUtils.random(5, 10);
        mood = computeStat(mood,15);
        isActive = false;
        setAnimation(Assets.pet_anim_happy);
    }

    public void sad(){
        isActive = false;
        setAnimation(Assets.pet_anim_take_med);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(Light.getInstance().isActive()) batch.draw(Assets.texture_pet_shadow,shadowPosition.x,shadowPosition.y);
        batch.draw(getCurrentAnimation().getKeyFrame(stateTime),getPosition().x,
                getPosition().y);
        drawEmotion(batch);
    }

    public void update(float delta){
        stateTime+=delta;
        if(currentAnimation != Assets.pet_anim_idle && !currentAnimation.getPlayMode().equals(Animation.PlayMode.LOOP)){
            if(currentAnimation.isAnimationFinished(stateTime)){
                setAnimation(Assets.pet_anim_idle);
                isActive = true;
            }
        }
        checkEmotion();
    }

    private void drawEmotion(SpriteBatch batch){
        if(showemotion)
        batch.draw(Assets.emotion_textureRegions[CURRENT_EMOTION] , POSX + 100 , POSY + 128);

    }

    public void setAnimation(Animation anim){
        stateTime = 0;
        currentAnimation = anim;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void log(String s) {
        Gdx.app.error(TAG, s);
    }

    public float getStateTime() {
        return stateTime;
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public Vector2 getShadowPosition() {
        return shadowPosition;
    }

    public boolean isSick() {
        return isSick;
    }

    public void saveData(){
        GamePreferences.instance.save(mood,hunger,health,stamina);
    }

    public void loadData(){


    }

}
