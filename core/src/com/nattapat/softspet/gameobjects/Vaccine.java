package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/21/2016 AD.
 */
public class Vaccine {
    private Vector2 position;
    public boolean active;
    private Animation anim;
    private float stateTime;
    public Vaccine(){
        position = new Vector2(230, Constants.VIEWPORT_HEIGHT - 300);
        stateTime = 0;
        anim = Assets.vaccine_anim;
    }
    public void update(float delta){
        stateTime+=delta;
    }
    public void inject(){
        active = true;
        stateTime = 0;
        anim = Assets.vaccine_anim;
        Gdx.app.error("check ","pet is Sick ? = "+Pet.getInstance().isSick());
    }

    public float getStateTime() {
        return stateTime;
    }

    public Vector2 getPosition() {
        return position;
    }
}
