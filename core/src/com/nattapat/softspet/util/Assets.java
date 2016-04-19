package com.nattapat.softspet.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class Assets {
    private static Texture texture_bg;
    private static Texture texture_button;
    private static Texture texture_pet;

    public static TextureRegion BG;

    public static TextureRegion food_button_down;
    public static TextureRegion food_button_up;
    public static TextureRegion med_button_down;
    public static TextureRegion med_button_up;
    public static TextureRegion light_button_down;
    public static TextureRegion light_button_up;
    public static TextureRegion toilet_button_down;
    public static TextureRegion toilet_button_up;
    public static TextureRegion minigame_button_down;
    public static TextureRegion minigame_button_up;

    public static Animation pet_anim_idle;
    public static Animation pet_anim_eat;
    public static Animation pet_anim_take_med;
    public static Animation pet_anim_sleep;
    public static Animation pet_anim_take_say_no;
    public static Animation pet_anim_happy;
    public static Array<TextureRegion> texutreArray_pets = new Array<TextureRegion>();

    public static void load() {
        loadBG();
        loadButton();
        loadPetAsset();
    }

    private static void loadPetAsset() {
        slipTexturePetToArrayTextureRegions();
        setAnimPetIdle();
        setAnimPetEat();
        setAnimPetTakeMed();
        setAnimPetSleep();
        setAnimPetSayNo();
        setAnimPetHappy();

    }

    private static void slipTexturePetToArrayTextureRegions() {
        texture_pet = new Texture(Gdx.files.internal("pet.png"));
        int x = 0;
        int y = 0;
        int count_texture_pet = 0;
        for (int i = 0; i < 4; i++) {
            count_texture_pet++;
            if(count_texture_pet>14)break;
            x=0;
            for (int j = 0; j < 4; j++) {
                texutreArray_pets.add(new TextureRegion(texture_pet, x, y, 128, 128));
                x += 128;
            }
            y += 128;
        }
    }

    private static void setAnimPetHappy() {
        TextureRegion[] pet_textureRegion_happy = new TextureRegion[2];
        pet_textureRegion_happy[0] = texutreArray_pets.get(12);
        pet_textureRegion_happy[1] = texutreArray_pets.get(13);
        pet_anim_happy = new Animation(0.8f,pet_textureRegion_happy);
        pet_anim_happy.setPlayMode(LOOP);
    }

    private static void setAnimPetSayNo() {
        TextureRegion[] pet_textureRegion_say_no = new TextureRegion[2];
        pet_textureRegion_say_no[0] = texutreArray_pets.get(10);
        pet_textureRegion_say_no[1] = texutreArray_pets.get(11);
        pet_anim_take_say_no = new Animation(0.8f,pet_textureRegion_say_no);
        pet_anim_take_say_no.setPlayMode(LOOP);
    }

    private static void setAnimPetSleep() {
        TextureRegion[] pet_textureRegion_sleep = new TextureRegion[2];
        pet_textureRegion_sleep[0] = texutreArray_pets.get(8);
        pet_textureRegion_sleep[1] = texutreArray_pets.get(9);
        pet_anim_sleep = new Animation(0.8f,pet_textureRegion_sleep);
        pet_anim_sleep.setPlayMode(LOOP);
    }

    private static void setAnimPetTakeMed() {
        TextureRegion[] pet_textureRegion_take_med = new TextureRegion[4];
        pet_textureRegion_take_med[0] = texutreArray_pets.get(5);
        pet_textureRegion_take_med[1] = texutreArray_pets.get(6);
        pet_textureRegion_take_med[2] = texutreArray_pets.get(5);
        pet_textureRegion_take_med[3] = texutreArray_pets.get(7);
        pet_anim_take_med = new Animation(0.5f,pet_textureRegion_take_med);
        pet_anim_take_med.setPlayMode(LOOP);
    }

    private static void setAnimPetEat() {
        TextureRegion[] pet_textureRegion_eat = new TextureRegion[2];
        pet_textureRegion_eat[0] = texutreArray_pets.get(0);
        pet_textureRegion_eat[1] = texutreArray_pets.get(4);
        pet_anim_eat = new Animation(0.8f,pet_textureRegion_eat);
        pet_anim_eat.setPlayMode(LOOP);
    }

    private static void setAnimPetIdle() {
        TextureRegion[] pet_textureRegion_idle = new TextureRegion[4];
        for(int i = 0 ; i<4 ; i++){
            pet_textureRegion_idle[i] = texutreArray_pets.get(i);
        }
        pet_anim_idle = new Animation(0.5f,pet_textureRegion_idle);
        pet_anim_idle.setPlayMode(LOOP);
    }


    private static void loadButton() {
        texture_button = new Texture(Gdx.files.internal("button.png"));
        food_button_up = new TextureRegion(texture_button, 0, 0, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        food_button_down = new TextureRegion(texture_button, 64, 0, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        med_button_up = new TextureRegion(texture_button, 0, 64, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        med_button_down = new TextureRegion(texture_button, 64, 64, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        light_button_up = new TextureRegion(texture_button, 128, 0, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        light_button_down = new TextureRegion(texture_button, 192, 0, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        toilet_button_up = new TextureRegion(texture_button, 128, 64, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);
        toilet_button_down = new TextureRegion(texture_button, 192, 64, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);

        minigame_button_up = new TextureRegion(texture_button, 0, 128, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);
        minigame_button_down = new TextureRegion(texture_button, 64, 128, texture_button.getWidth() / 4
                , texture_button.getHeight() / 4);
    }

    private static void loadBG() {
        texture_bg = new Texture(Gdx.files.internal("game_bg.png"));
        BG = new TextureRegion(texture_bg, 0, 0, texture_bg.getWidth(), texture_bg.getHeight());
    }

    public static void dispose() {

        texture_bg.dispose();
        texture_button.dispose();
        texture_pet.dispose();
    }

}
