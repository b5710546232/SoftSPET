package com.nattapat.softspet.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nattapat.softspet.util.Assets;
import com.nattapat.softspet.util.Clock;
import com.nattapat.softspet.util.Constants;


/**
 * Created by nattapat on 4/6/2016 AD.
 */
public class GameRenderer implements Disposable {
    private static final String TAG = GameRenderer.class.getName();
    private GameWorld world;
    private OrthographicCamera camera;
    private Viewport gameViewport;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private BitmapFont clockfont;
    private Clock clock;



    public GameRenderer(GameWorld world ){
        this.world = world;
        initCamera();
        init();
    }

    private void initCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        gameViewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,camera);
        camera.update();



    }

    private void init() {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        clockfont = new BitmapFont(Gdx.files.internal("Visitor.fnt"));
        clockfont.getRegion().getTexture().setFilter(
                Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        clockfont.getData().setScale(2.0f, 2.0f);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        clock = new Clock();
    }


    public void render() {

        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();
        drawBG();
        renderGameObject();
        renderGuiFpsCounter(batch);
        drawLight();
        batch.end();

        batch.begin();
        clock.render(batch, clockfont);
        drawLOGO();
        batch.end();
    }

    private void renderGameObject(){
        world.getWorldContainer().render(batch);
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

    private void drawBG(){
        batch.draw(Assets.BG, 0, 0);
    }

    private void drawLOGO(){ batch.draw(Assets.logo_texture,10,Constants.VIEWPORT_HEIGHT - 110);}

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


    public void resize(int width, int height){
        gameViewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        clockfont.dispose();
    }



}

