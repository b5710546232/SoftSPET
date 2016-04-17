package com.nattapat.softspet.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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
    public static Array<TextureRegion> pets = new Array<TextureRegion>();

    public static void load() {

        texture_bg = new Texture(Gdx.files.internal("game_bg.png"));
        BG = new TextureRegion(texture_bg, 0, 0, texture_bg.getWidth(), texture_bg.getHeight());
//        BG.flip(false,true);

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



        texture_pet = new Texture(Gdx.files.internal("pet.png"));
        int x = 0;
        int y = 0;
        for (int i = 0; i < 4; i++) {
            x=0;
            for (int j = 0; j < 4; j++) {
                pets.add(new TextureRegion(texture_pet, x, y, 128, 128));
                x += 128;
            }
            y += 128;
        }

        pet_anim_idle = new Animation(3f,pets);


    }

    public static void dispose() {

        texture_bg.dispose();
        texture_button.dispose();
        texture_pet.dispose();
    }

}
