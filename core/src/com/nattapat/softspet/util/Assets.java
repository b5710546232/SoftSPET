package com.nattapat.softspet.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class Assets implements Disposable, AssetErrorListener {
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
    public static Animation vaccine_anim;
    public static Array<TextureRegion> texutreArray_pets = new Array<TextureRegion>();
    public static Texture texture_pet_shadow;
    public static Texture texture_wave_cleaner;

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    private static Texture texture_vaccine;
    private Texture food_texture;
    public static TextureRegion bread_btn_up;
    public static TextureRegion meat_btn_up;
    public static TextureRegion bread_btn_down;
    public static TextureRegion meat_btn_down;

    public Assets() {
    }

    public void init(AssetManager asm) {
        assetManager = asm;
        assetManager.setErrorListener(this);
        load();
    }

    public void load() {
        loadBG();
        loadButton();
        loadPetAsset();
        loadPetEffect();
        loadSceneEffect();
        loadVaccine();
        loadFood();
    }

    private void loadFood() {
        food_texture = new Texture(Gdx.files.internal("food_button_spritesheet.png"));
        bread_btn_up = new TextureRegion(food_texture,0,0,128,128);
        bread_btn_down = new TextureRegion(food_texture,0,128,128,128);

        meat_btn_up = new TextureRegion(food_texture,128,0,128,128);
        meat_btn_down = new TextureRegion(food_texture,128,128,128,128);
    }

    private void loadVaccine() {
        texture_vaccine = new Texture(Gdx.files.internal("vaccine.png"));
        texture_vaccine.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion[] vaccine_textureRegion = new TextureRegion[5];
        vaccine_textureRegion[0] = new TextureRegion(texture_vaccine,0,0,64,64);
        vaccine_textureRegion[1] = new TextureRegion(texture_vaccine,64,0,64,64);
        vaccine_textureRegion[2] = new TextureRegion(texture_vaccine,128,0,64,64);
        vaccine_textureRegion[3] = new TextureRegion(texture_vaccine,192,0,64,64);
        vaccine_textureRegion[4] = new TextureRegion(texture_vaccine,256,0,64,64);
        vaccine_anim = new Animation(0.5f,vaccine_textureRegion);
        vaccine_anim.setPlayMode(NORMAL);

    }

    private void loadSceneEffect() {

        texture_wave_cleaner = new Texture(Gdx.files.internal("cleaner_wave.png"));
    }

    private void loadPetEffect() {
        texture_pet_shadow = new Texture(Gdx.files.internal("pet_shadow.png"));
    }

    private void loadPetAsset() {
        slipTexturePetToArrayTextureRegions();
        setAnimPetIdle();
        setAnimPetEat();
        setAnimPetTakeMed();
        setAnimPetSleep();
        setAnimPetSayNo();
        setAnimPetHappy();

    }

    private void slipTexturePetToArrayTextureRegions() {
//        assetManager.load(Gdx.files.internal("pet.png").toString(), Texture.class);
//        assetManager.finishLoadingAsset("pet.png");
//        texture_pet = assetManager.get(Gdx.files.internal("pet.png").toString(), Texture.class);
        texture_pet = new Texture(Gdx.files.internal("pet.png"));
        int x = 0;
        int y = 0;
        int count_texture_pet = 0;
        for (int i = 0; i < 4; i++) {
            count_texture_pet++;
            if (count_texture_pet > 14) break;
            x = 0;
            for (int j = 0; j < 4; j++) {
                texutreArray_pets.add(new TextureRegion(texture_pet, x, y, 128, 128));
                x += 128;
            }
            y += 128;
        }
    }

    private void setAnimPetHappy() {
        TextureRegion[] pet_textureRegion_happy = new TextureRegion[4];
        pet_textureRegion_happy[0] = texutreArray_pets.get(12);
        pet_textureRegion_happy[1] = texutreArray_pets.get(13);
        pet_textureRegion_happy[2] = texutreArray_pets.get(12);
        pet_textureRegion_happy[3] = texutreArray_pets.get(13);
        pet_anim_happy = new Animation(1, pet_textureRegion_happy);
        pet_anim_happy.setPlayMode(NORMAL);
    }

    private void setAnimPetSayNo() {
        TextureRegion[] pet_textureRegion_say_no = new TextureRegion[4];
        pet_textureRegion_say_no[0] = texutreArray_pets.get(10);
        pet_textureRegion_say_no[1] = texutreArray_pets.get(11);
        pet_textureRegion_say_no[2] = texutreArray_pets.get(10);
        pet_textureRegion_say_no[3] = texutreArray_pets.get(11);
        pet_anim_take_say_no = new Animation(0.8f, pet_textureRegion_say_no);
        pet_anim_take_say_no.setPlayMode(NORMAL);
    }

    private void setAnimPetSleep() {
        TextureRegion[] pet_textureRegion_sleep = new TextureRegion[2];
        pet_textureRegion_sleep[0] = texutreArray_pets.get(8);
        pet_textureRegion_sleep[1] = texutreArray_pets.get(9);
        pet_anim_sleep = new Animation(0.8f, pet_textureRegion_sleep);
        pet_anim_sleep.setPlayMode(LOOP);
    }

    private void setAnimPetTakeMed() {
        TextureRegion[] pet_textureRegion_take_med = new TextureRegion[6];
        pet_textureRegion_take_med[0] = texutreArray_pets.get(5);
        pet_textureRegion_take_med[1] = texutreArray_pets.get(6);
        pet_textureRegion_take_med[2] = texutreArray_pets.get(5);
        pet_textureRegion_take_med[3] = texutreArray_pets.get(7);
        pet_textureRegion_take_med[4] = texutreArray_pets.get(6);
        pet_textureRegion_take_med[5] = texutreArray_pets.get(5);
        pet_anim_take_med = new Animation(0.5f, pet_textureRegion_take_med);
        pet_anim_take_med.setPlayMode(NORMAL);
    }

    private void setAnimPetEat() {
        TextureRegion[] pet_textureRegion_eat = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0)
                pet_textureRegion_eat[i] = texutreArray_pets.get(0);
            else
                pet_textureRegion_eat[i] = texutreArray_pets.get(4);

        }
        pet_anim_eat = new Animation(0.8f, pet_textureRegion_eat);
        pet_anim_eat.setPlayMode(NORMAL);
    }

    private void setAnimPetIdle() {
        TextureRegion[] pet_textureRegion_idle = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            pet_textureRegion_idle[i] = texutreArray_pets.get(i);
        }
        pet_anim_idle = new Animation(0.5f, pet_textureRegion_idle);
        pet_anim_idle.setPlayMode(LOOP);
    }


    private void loadButton() {
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

    private void loadBG() {
        texture_bg = new Texture(Gdx.files.internal("game_bg.png"));
        BG = new TextureRegion(texture_bg, 0, 0, texture_bg.getWidth(), texture_bg.getHeight());
    }

    @Override
    public void dispose() {
        texture_pet_shadow.dispose();
        texture_bg.dispose();
        texture_button.dispose();
        texture_pet.dispose();
        assetManager.dispose();
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" +
                asset.fileName + "'", (Exception) throwable);
    }
}
