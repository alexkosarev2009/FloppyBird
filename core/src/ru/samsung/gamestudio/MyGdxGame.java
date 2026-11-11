package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.samsung.gamestudio.screens.GameScreen;
import ru.samsung.gamestudio.screens.MenuScreen;
import ru.samsung.gamestudio.screens.RestartScreen;

public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public GameScreen gameScreen;
    public RestartScreen restartScreen;
    public MenuScreen menuScreen;
    public OrthographicCamera camera;
    public FitViewport viewport;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        viewport = new FitViewport(SCR_WIDTH, SCR_HEIGHT, camera);

        gameScreen = new GameScreen(this);
        restartScreen = new RestartScreen(this);
        menuScreen = new MenuScreen(this);

        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (gameScreen != null) gameScreen.dispose();
        if (restartScreen != null) restartScreen.dispose();
        if (menuScreen != null) menuScreen.dispose();
    }
}
