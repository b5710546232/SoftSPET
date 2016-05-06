package com.nattapat.softspet.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.nattapat.softspet.gameobjects.Pet;
import com.nattapat.softspet.gameworld.GameWorld;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;
import com.nattapat.softspet.util.RandomGenerator;
import com.badlogic.gdx.utils.Timer;


/**
 * Created by nattapat on 4/28/2016 AD.
 */
public class MiniGameScreen implements Screen {
    private static final String TAG = MiniGameScreen.class.getName();
    private final int ROCK = 0;
    private final int SCISSORS = 1;
    private final int PAPER = 2;

    private final int WIN = 0;
    private final int LOSE = 1;
    private final int DRAW = 2;
    private final int NONE = -1;

    private int currentResult = NONE;
    private int userChoice = 0;
    private GameWorld world;
    private Stage stage;
    private Image BG;
    private SpriteBatch batch;
    private ImageButton rockButton;
    private ImageButton paperButton;
    private ImageButton scissorsButton;
    private Game game;
    private final float POS_Y = 256;
    private final float INITPOS_X = -64;
    private final float FINALPOS_X = 360 + 64;
    private final float MID_X = 116;
    private int STATE = 0;
    private boolean isGameStop;
    private boolean active = false;
    private Pet pet;
    private RandomGenerator rng = RandomGenerator.instance;
    private TextureRegion[] petChoice;
    private int count = 0;

    public MiniGameScreen(Game game) {

        this.game = (SoftspetMain) game;
        this.world = ((SoftspetMain) game).getPlayScreen().getWorld();
        this.pet = Pet.getInstance();
        batch = new SpriteBatch();
        initPetchoice();
        Timer.schedule(new Timer.Task() {
            public void run() {
                if (!isGameStop) {
                    rng.random();
//                    Gdx.app.log(TAG, "RUN");
                }
            }

        }, 0, 0.5f);
    }

    private void initPetchoice() {
        petChoice = new TextureRegion[3];
        petChoice[0] = Assets.rock_btn_up;
        petChoice[1] = Assets.scissors_btn_up;
        petChoice[2] = Assets.paper_btn_up;
    }


    @Override
    public void show() {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT));
        rebuildStage();
        Gdx.input.setInputProcessor(stage);
        reset();
    }

    public void rebuildStage() {
        Table layerBackground = buildBackgroundLayer();
        Table layerRockButton = buildRockButton();
        Table layerPaperButton = buildPaperButton();
        Table layerScissorsButton = buildScissorsButton();
        stage.clear();
        Stack stack = new Stack();
        stack.setSize(Constants.VIEWPORT_WIDTH,
                Constants.VIEWPORT_HEIGHT);
        stage.addActor(stack);
        stack.add(layerBackground);
        stack.add(layerRockButton);
        stack.add(layerPaperButton);
        stack.add(layerScissorsButton);
        rockButton.setPosition(MID_X - 118, 5);
        paperButton.setPosition(MID_X, 5);
        scissorsButton.setPosition(MID_X + 118, 5);
        initListener();
    }

    private void initListener() {
        rockButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!isGameStop) {
                    userChoice = ROCK;
                    paperButton.setVisible(false);
                    scissorsButton.setVisible(false);
                    stopGame();
                    checkWin();
                }
            }
        });
        paperButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!isGameStop) {
                    userChoice = PAPER;
                    rockButton.setVisible(false);
                    scissorsButton.setVisible(false);
                    stopGame();
                    checkWin();
                }


            }
        });
        scissorsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!isGameStop) {
                    userChoice = SCISSORS;
                    paperButton.setVisible(false);
                    rockButton.setVisible(false);
                    stopGame();
                    checkWin();
                }

            }
        });
    }

    private void reset() {
        isGameStop = false;
        currentResult = NONE;
        scissorsButton.setVisible(true);
        paperButton.setVisible(true);
        rockButton.setVisible(true);
        userChoice = NONE;
    }

    private void checkWin() {
        if (userChoice == rng.getValue()) {
            Gdx.app.error(TAG, "----- draw -----");
            currentResult = DRAW;
            Timer.schedule(new Timer.Task() {
                               @Override
                               public void run() {
                                   reset();
                                   Gdx.app.log(TAG, "DRAW");
                               }
                           }
                    , 1        //    (delay)
                    , 0   //    (seconds)
                    , 0
            );

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            reset();


        } else if ((userChoice == ROCK && rng.getValue() == SCISSORS) ||
                (userChoice == PAPER && rng.getValue() == ROCK) ||
                (userChoice == SCISSORS && rng.getValue() == PAPER)
                ) {
            Gdx.app.error(TAG, "----- user win -----");
            currentResult = WIN;
            Timer.schedule(new Timer.Task() {
                               @Override
                               public void run() {
                                   Gdx.app.log(TAG, "WIN");

                                   backToPlayScreen();

                               }
                           }
                    , 1        //    (delay)
                    , 0   //    (seconds)
                    , 0
            );


        } else {
            Gdx.app.error(TAG, "----- user lose -----");
            currentResult = LOSE;
            Timer.schedule(new Timer.Task() {
                               @Override
                               public void run() {
                                   Gdx.app.log(TAG, "LOSE");
                                   backToPlayScreen();
                                   pet.happy();

                               }
                           }
                    , 1        //    (delay)
                    , 0   //    (seconds)
                    , 0
            );
        }

    }

    private void backToPlayScreen() {
        game.setScreen(((SoftspetMain) game).getPlayScreen());
    }

    private void stopGame() {
        isGameStop = true;
        rng.random();
    }

    private Table buildBackgroundLayer() {
        Table layer = new Table();
        BG = new Image(new TextureRegionDrawable(Assets.BG));
        layer.add(BG);
        return layer;
    }

    private Table buildRockButton() {
        Table layer = new Table();
        rockButton = new ImageButton(new TextureRegionDrawable(Assets.rock_btn_up),
                new TextureRegionDrawable(Assets.rock_btn_down));
        layer.addActor(rockButton);
        return layer;
    }

    private Table buildPaperButton() {
        Table layer = new Table();
        paperButton = new ImageButton(new TextureRegionDrawable(Assets.paper_btn_up),
                new TextureRegionDrawable(Assets.paper_btn_down));
        layer.addActor(paperButton);
        return layer;
    }

    private Table buildScissorsButton() {
        Table layer = new Table();
        scissorsButton = new ImageButton(new TextureRegionDrawable(Assets.scissors_btn_up),
                new TextureRegionDrawable(Assets.scissors_btn_down));
        layer.addActor(scissorsButton);
        return layer;
    }

    @Override
    public void render(float deltaTime) {
        update(deltaTime);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.enableBlending();
        batch.begin();
        batch.draw(petChoice[rng.getValue()], MID_X, 507);
        pet.render(batch);
        draw_text_win();
        draw_text_draw();
        draw_text_lose();
        batch.end();
        if (Gdx.input.justTouched() && !isGameStop) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            Vector3 inputs = new Vector3(x, y, 0);
            stage.getCamera().unproject(inputs);
//            Gdx.app.error("input : ", " y is " + inputs.y);
//            Gdx.app.error("input : ", " x is " + inputs.x);
            if ((inputs.y >= 135)) game.setScreen(((SoftspetMain) game).getPlayScreen());
        }
    }

    private void update(float deltaTime) {
        stage.act(deltaTime);
        pet.update(deltaTime);

    }

    private void draw_text_win() {
        if (currentResult == WIN)
            batch.draw(Assets.win_text, MID_X, 400);
    }

    private void draw_text_draw() {
        if (currentResult == DRAW)
            batch.draw(Assets.draw_text, MID_X, 400);
    }

    private void draw_text_lose() {
        if (currentResult == LOSE)
            batch.draw(Assets.lose_text, MID_X, 400);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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
        batch.dispose();
    }
}
