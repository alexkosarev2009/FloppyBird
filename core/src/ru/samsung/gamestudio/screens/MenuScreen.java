package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.ui.MovingBackground;
import ru.samsung.gamestudio.components.ui.TextButton;

public class MenuScreen implements Screen {

    public MyGdxGame myGdxGame;

    private long highScore;

    private MovingBackground movingBackground;

    private final TextButton startGameButton;
    private final TextButton quitGameButton;

    private BitmapFont font;


    public MenuScreen(MyGdxGame myGdxGame) {
        this.movingBackground = new MovingBackground(new Texture("backgrounds/game_bg.png"));
        this.myGdxGame = myGdxGame;

        this.startGameButton = new TextButton(MyGdxGame.SCR_WIDTH / 2, 400, "Start");
        this.quitGameButton = new TextButton(MyGdxGame.SCR_WIDTH / 2, 200, "Quit");

        font = new BitmapFont();
        font.getData().setScale(4f);
        font.setColor(Color.GOLD);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        highScore = Gdx.app.getPreferences("MyFlappyBirdPreferences").getLong("highScore", 0);

        movingBackground.move(delta);
        Vector3 touch = new Vector3(-1, -1, -1);

        if (Gdx.input.justTouched()) {
            touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
        if (startGameButton.isClicked(touch.x, touch.y)) {
            myGdxGame.gameScreen = new GameScreen(myGdxGame);
            myGdxGame.setScreen(myGdxGame.gameScreen);
        }
        if (quitGameButton.isClicked(touch.x, touch.y)) {
            Gdx.app.exit();
        }
        ScreenUtils.clear(0, 0, 0, 1);

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        startGameButton.draw(myGdxGame.batch);
        quitGameButton.draw(myGdxGame.batch);
        font.draw(myGdxGame.batch, "HIGHSCORE: " + highScore, MyGdxGame.SCR_WIDTH / 2 - 600, 420);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        myGdxGame.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        movingBackground.dispose();
        startGameButton.dispose();
        quitGameButton.dispose();
        font.dispose();
    }

    public long getHighScore() {
        return highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }
}
