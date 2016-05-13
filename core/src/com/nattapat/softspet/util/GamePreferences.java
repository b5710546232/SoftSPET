package com.nattapat.softspet.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by nattapat on 5/9/2016 AD.
 */
public class GamePreferences {
        public static final String TAG = GamePreferences.class.getName();
        public static final GamePreferences instance = new GamePreferences();


        public float mood;
        public float hunger;
        public float health;
        public float stamina;
        private Preferences prefs;

        private GamePreferences () {
                prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
        }

        public void load () {
                prefs.getFloat("mood");
                prefs.getFloat("hunger");
                prefs.getFloat("health");
                prefs.getFloat("stamina");

        }
        public void save (float mood , float hunger , float health , float stamina) {
                prefs.putFloat("mood",mood);
                prefs.putFloat("hunger",hunger);
                prefs.putFloat("health",health);
                prefs.putFloat("stamina",stamina);
                prefs.flush();
        }


}
