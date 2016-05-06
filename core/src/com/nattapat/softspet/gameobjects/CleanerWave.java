package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/20/2016 AD.
 */
public class CleanerWave implements GameObject {
    private  Pet pet;
    private final int LIMITDISTANCE = -200;
    private final int SPEED = 360;
    private Vector2 position;
    private boolean active;
    public CleanerWave(){
        position = new Vector2();
        pet = Pet.getInstance();
        reset();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(Assets.texture_wave_cleaner,position.x, position.y);
    }

    public void clean(){
        active = true;
    }
    public void update(float delta){
        if(active){
            position.x-= SPEED*delta;
            if( position.x<= LIMITDISTANCE ){
                reset();
                pet.happy();
            }
        }

    }
    private void reset(){
        active = false;
        position.set(Constants.VIEWPORT_WIDTH/1.5f,140);
    }

    public Vector2 getPosition() {
        return position;
    }
}
