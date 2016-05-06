package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/21/2016 AD.
 */
public class Vaccine implements GameObject {
    private Vector2 position;
    public boolean active;
    private float stateTime;
    public Vaccine(){
        position = new Vector2(230, Constants.VIEWPORT_HEIGHT - 300);
        stateTime = 0;
    }

    @Override
    public void render(SpriteBatch batch) {
        if(active){
            if(Assets.vaccine_anim.isAnimationFinished(stateTime)){
                active = false;
            } else{
                batch.draw(Assets.vaccine_anim.getKeyFrame(stateTime),
                        position.x,position.y);
            }

        }
    }

    public void update(float delta){
        stateTime+=delta;
    }
    public void inject(Pet pet){
        if(pet.isSick()) {
            active = true;
            stateTime = 0;
            Gdx.app.error("check ", "pet is Sick ? = " + Pet.getInstance().isSick());
        }
        pet.takeMedicine();
    }

    public float getStateTime() {
        return stateTime;
    }

    public Vector2 getPosition() {
        return position;
    }
}
