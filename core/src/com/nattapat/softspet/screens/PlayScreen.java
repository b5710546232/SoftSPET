package com.nattapat.softspet.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nattapat.softspet.SoftspetMain;
import com.nattapat.softspet.gameworld.GameRenderer;
import com.nattapat.softspet.gameworld.GameWorld;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/6/2016 AD.
 */
public class PlayScreen implements Screen {
    private static final String TAG = PlayScreen.class.getName();
    private GameRenderer renderer;
    private GameWorld world;
    private Stage stage;
    private float stateTime = 0;
    private SoftspetMain game;
    private ImageButton foodButton;
    private ImageButton medButton;
    private ImageButton lightButton;
    private ImageButton toiletButton;
    private ImageButton miniGameButton;


    public PlayScreen(Game game) {
        this.game = (SoftspetMain) game;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float gameWidth = Constants.VIEWPORT_WIDTH;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        world = new GameWorld(game);
        renderer = new GameRenderer(world, (int) gameHeight);
    }

    private void initButton() {
        foodButton = new ImageButton(new TextureRegionDrawable(Assets.food_button_up),
                new TextureRegionDrawable(Assets.food_button_down));
        foodButton.setPosition(10, 30);
        stage.addActor(foodButton);

        medButton = new ImageButton(new TextureRegionDrawable(Assets.med_button_up),
                new TextureRegionDrawable(Assets.med_button_down));
        medButton.setPosition(79, 30);
        stage.addActor(medButton);

        lightButton = new ImageButton(new TextureRegionDrawable(Assets.light_button_up),
                new TextureRegionDrawable(Assets.light_button_down));
        lightButton.setPosition(148, 30);
        stage.addActor(lightButton);

        toiletButton = new ImageButton(new TextureRegionDrawable(Assets.toilet_button_up),
                new TextureRegionDrawable(Assets.toilet_button_down));
        toiletButton.setPosition(217, 30);
        stage.addActor(toiletButton);

        miniGameButton = new ImageButton(new TextureRegionDrawable(Assets.minigame_button_up),
                new TextureRegionDrawable(Assets.minigame_button_down));
        miniGameButton.setPosition(286, 30);
        stage.addActor(miniGameButton);

        initListener();

    }

    public GameWorld getWorld() {
        return world;
    }

    private void initListener() {
        foodButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                world.selectFood();
            }
        });

        medButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                world.injectVaccineToPet();


            }
        });

        lightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                world.switchLight();
            }
        });

        toiletButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                world.cleanPet();
            }
        });

        miniGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                world.playMiniGame();
            }
        });

    }


    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT));
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        initButton();
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        //Draw a black bg. This prevents flickering.
        Gdx.gl.glClearColor(1, 0, 0, 1);
        //// Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        world.update(delta);
        renderer.render(delta, stateTime);
        stage.act(delta);
        stage.draw();
        if (Gdx.input.justTouched() && world.isActive()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 inputs = new Vector3(x, y, 0);
            stage.getCamera().unproject(inputs);
//            Gdx.app.error(TAG, " pos x = " + inputs.x + " ||  posy = " + inputs.y);
            if ((inputs.y >= 220 && inputs.y <= 360) && (inputs.x >= 115 && inputs.x <= 245)) {
                world.showEmotion();

            }
            else{
                world.hideEmotion();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {
        Gdx.app.error(TAG, " pause ");

    }

    @Override
    public void resume() {
        world.resume();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        stage.dispose();

    }
}
