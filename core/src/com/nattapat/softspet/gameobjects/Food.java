package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by nattapat on 4/8/2016 AD.
 */
public abstract  class Food {
    private int huggerPoint;
    protected float stateTime;
    private Vector2 position;

    public Food(int x , int y , int point){
        position = new Vector2(x,y);
        huggerPoint = point;
        stateTime = 0;
    }
    public int getHuggerPoint() {
        return huggerPoint;
    }
    public abstract void eatten();
    public  abstract  void update(float delta);
    public float getStateTime() {
        return stateTime;
    }


    public Vector2 getPosition() {
        return position;
    }



}
