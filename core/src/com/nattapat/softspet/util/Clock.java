package com.nattapat.softspet.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Calendar;
import java.util.Formatter;
/**
 * Created by nattapat on 4/20/2016 AD.
 */
public class Clock {
    private Formatter fmt;
    private String text;
    public Clock(){

    }

    public void render (SpriteBatch batch,BitmapFont font){
        float x = Constants.VIEWPORT_WIDTH - 175;
        float y = Constants.VIEWPORT_HEIGHT - 60;
        font.setColor(1, 1, 1, 1);
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
//        int sec = c.get(Calendar.SECOND);
//        fmt = new Formatter();
//        fmt.format("%tT",c);
//        text = fmt.toString();
//        fmt.close();
        text = String.format("%02d:%02d",hour,min);
        font.draw(batch, text , x, y);
    }
}
