package com.nattapat.softspet.gameworld;

/**
 * Created by nattapat on 4/15/2016 AD.
 */
public class Light {
    private boolean active;
    public static Light instance;
    public Light(){
        active = true;
    }


    public void click(){
        active = !active;
    }
    public boolean isActive(){
        return active;
    }
}
