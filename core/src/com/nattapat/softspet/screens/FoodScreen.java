package com.nattapat.softspet.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
public class FoodScreen implements Screen {
    private static final String TAG = FoodScreen.class.getName();
    private GameRenderer renderer;
    private GameWorld world;
    private Stage stage;
    private float stateTime = 0;
    private Image BG;
    private ImageButton breadButton;
    private ImageButton meatButton;
    private Game game;
    private final float POS_Y = 256;
    private final float INITPOS_X = -64;
    private final float FINALPOS_X = 360+64;
    private final float MID_X = 116;
    private int STATE = 0;
    private boolean active = false;

    public FoodScreen(Game game){
    this.game = game;
    }



    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT));
        rebuildStage();
        Gdx.input.setInputProcessor(stage);
    }

    public void rebuildStage(){
        Table layerBackground = buildBackgroundLayer();
        Table layerMeatButton = buildMeatButton();
        Table layerBreadButton = buildBreadButton();
        stage.clear();
        Stack stack = new Stack();
        stack.setSize(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT);
        stage.addActor(stack);
        stack.add(layerBackground);
        stack.add(layerMeatButton);
        stack.add(layerBreadButton);
        breadButton.setPosition(MID_X, POS_Y + 64);
        meatButton.setPosition(MID_X, POS_Y - 64);
        initListener();
    }

    private void initListener() {
        breadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ((SoftspetMain) game).getPlayScreen().getWorld().getPet().eat(10);
                game.setScreen(((SoftspetMain) game).getPlayScreen());

            }
        });
        meatButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ((SoftspetMain) game).getPlayScreen().getWorld().getPet().eat(10);
                game.setScreen(((SoftspetMain) game).getPlayScreen());

            }
        });
    }

    private Table buildBackgroundLayer(){
        Table layer  = new Table();
        BG = new Image(new TextureRegionDrawable(Assets.BG));
        layer.add(BG);
        return layer;
    }

    private Table buildMeatButton(){
        Table layer  = new Table();
        meatButton = new ImageButton(new TextureRegionDrawable(Assets.meat_btn_up),
                new TextureRegionDrawable( Assets.meat_btn_down));
        layer.addActor(meatButton);
        return layer;
    }

    private Table buildBreadButton(){
        Table layer  = new Table();
        breadButton = new ImageButton(new TextureRegionDrawable(Assets.bread_btn_up),
                new TextureRegionDrawable( Assets.bread_btn_down));
        layer.addActor(breadButton);
        return layer;
    }

    @Override
    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(deltaTime);
        stage.draw();
        if(Gdx.input.justTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 inputs = new Vector3(x, y, 0);
            stage.getCamera().unproject(inputs);
//            Gdx.app.error("input : ", " y is " + inputs.y);
//            Gdx.app.error("input : "," x is "+ inputs.x);
            if((inputs.y<=180 || inputs.y>=420 ) || (inputs.x<=110 || inputs.x>=230))
            game.setScreen(((SoftspetMain) game).getPlayScreen());

        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);

    }

    @Override
    public void pause() {
        Gdx.app.error(TAG, " pause ");

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
