package com.nattapat.softspet.gameobjects;

/**
 * Created by nattapat on 4/25/2016 AD.
 */
public class Meat extends  Food {
    public boolean active;

    public  Meat (int x , int y , int point){
        super(x,y,point);
        active = false;
    }
    @Override
    public void eatten() {
        active = true;
        stateTime = 0;
    }

    @Override
    public void update(float delta) {

        stateTime+=delta;

    }
}
