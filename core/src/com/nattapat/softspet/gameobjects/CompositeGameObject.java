package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattapat on 6/3/2016 AD.
 */
public class CompositeGameObject implements GameObject {
    private List<GameObject> list ;

    public CompositeGameObject() {
        list = new ArrayList<GameObject>();
    }


    public void add(GameObject child){
        list.add(child);
    }

    @Override
    public void render(SpriteBatch batch) {
        for(GameObject child : list){
            child.render(batch);
        }

    }

    @Override
    public void update(float delta) {
        for(GameObject child : list){
            child.update(delta);
        }
    }
}
