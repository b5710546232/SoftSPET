package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nattapat on 5/2/2016 AD.
 */
public interface GameObject {

    public  void render (SpriteBatch batch);

    public void update(float delta);
}
