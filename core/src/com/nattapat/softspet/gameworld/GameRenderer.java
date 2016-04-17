package com.nattapat.softspet.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Constants;

/**
 * Created by nattapat on 4/6/2016 AD.
 */
public class GameRenderer implements Disposable {
    private static final String TAG = GameRenderer.class.getName();
    private GameWorld world;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;


    public GameRenderer(GameWorld world , int gameHeight ){
        this.world = world;
        initCamera(gameHeight);
        init();
    }

    private void initCamera(int gameHeight) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.update();



    }

    private void init() {

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

    }


    public void render(float delta, float stateTime) {

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();
        batch.draw(Assets.BG, 0, 0);
        drawPet(stateTime);
        renderGuiFpsCounter(batch);
        batch.end();
        drawLight();
    }


    private void renderGuiFpsCounter (SpriteBatch batch) {
        float x = camera.viewportWidth - 55;
        float y = camera.viewportHeight - 15;
        int fps = Gdx.graphics.getFramesPerSecond();
        BitmapFont fpsFont = font;
        if (fps >= 45) {
            fpsFont.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
            fpsFont.setColor(1, 1, 0, 1);
        } else {
            fpsFont.setColor(1, 0, 0, 1);
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y);
        fpsFont.setColor(1, 1, 1, 0.5f); // white
    }

    private void drawPet(float stateTime){
//        batch.draw(Assets.pets.get(0), world.getPet().getPosition().x,
//                world.getPet().getPosition().y);
        batch.draw(Assets.pet_anim_idle.getKeyFrame(stateTime),world.getPet().getPosition().x,
                world.getPet().getPosition().y);
    }

    private void drawLight(){
        if(world.getLight().isActive()) return;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0 ,0,0, 0.75f);
        shapeRenderer.rect(0, 0, Constants.VIEWPORT_WIDTH , Constants.VIEWPORT_HEIGHT );
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }



}

