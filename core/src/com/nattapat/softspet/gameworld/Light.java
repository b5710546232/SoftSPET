package com.nattapat.softspet.gameworld;

/**
 * Created by nattapat on 4/15/2016 AD.
 */
public class Light {
    private boolean active;
    private static Light instance = null;
    private Light(){
            active = true;

    }

    public static  Light getInstance(){
        if(instance ==null){
            instance = new Light();
        }
        return  instance;
    }


    public void click(){
        active = !active;
    }
    public boolean isActive(){
        return active;
    }
}
