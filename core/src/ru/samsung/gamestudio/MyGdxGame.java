package ru.samsung.gamestudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
    public GameScreen gameScreen;
    public Viewport viewport;
    public OrthographicCamera camera;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

	@Override
	public void create () {
		batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

//        viewport = new FillViewport(SCR_WIDTH, SCR_HEIGHT, camera);
//        viewport.apply();

        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
	}

	@Override
	public void dispose () {
		batch.dispose();
        if (screen != null) screen.dispose();
    }
}
