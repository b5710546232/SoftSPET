package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public class Food {
    private int huggerPoint;
    private Vector2 position;
    public Food(){
        position = new Vector2(30,380);
    }
    public int getHuggerPoint() {
        return huggerPoint;
    }

    public Vector2 getPosition() {
        return position;
    }



}
