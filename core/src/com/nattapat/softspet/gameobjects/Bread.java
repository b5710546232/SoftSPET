package com.nattapat.softspet.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nattapat.softspet.util.Assets;

/**
 * Created by nattapat on 4/25/2016 AD.
 */
public class Bread extends  Food  implements  GameObject{
    private boolean active;

    public  Bread (int x , int y , int point){
        super(x,y,point);
        active = false;
    }
    @Override
    public void eatten() {
        active = true;
        stateTime = 0;;
    }

    @Override
    public void render(SpriteBatch batch) {
        if(active){
            if(Assets.bread_food_anim.isAnimationFinished(stateTime)){
                active = false;
            }
            else{
                batch.draw(Assets.bread_food_anim.getKeyFrame(stateTime),
                        getPosition().x,getPosition().y);
            }
        }
    }

    @Override
    public void update(float delta) {

        stateTime+=delta;

    }
}
