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
import ru.samsung.gamestudio.components.ui.ScoreCounter;
import ru.samsung.gamestudio.components.ui.TextButton;

public class RestartScreen implements Screen {
    private final MyGdxGame myGdxGame;
    private final MovingBackground movingBackground;
    private final TextButton restartButton;
    private final TextButton goToMenuButton;
    private long score;
    private BitmapFont font;

    public void setScore(long score) {
        this.score = score;
    }

    public RestartScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        this.score = 0;
        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.WHITE);
        this.movingBackground = new MovingBackground(new Texture("backgrounds/restart_bg.png"));
        this.restartButton = new TextButton(100, 500, "Restart");
        this.goToMenuButton = new TextButton(100, 300, "Menu");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Vector3 touch = new Vector3(-1, -1, -1);

        if (Gdx.input.justTouched()) {
            touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }
        if (restartButton.isClicked(touch.x, touch.y)) {
            myGdxGame.gameScreen.restartGame();
            myGdxGame.setScreen(myGdxGame.gameScreen);
            Gdx.input.justTouched();
            return;
        }
        if (goToMenuButton.isClicked(touch.x, touch.y)) {
            myGdxGame.gameScreen.restartGame();
            myGdxGame.setScreen(myGdxGame.menuScreen);
            return;
        }
        ScreenUtils.clear(0, 0, 0, 1);


        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        restartButton.draw(myGdxGame.batch);
        goToMenuButton.draw(myGdxGame.batch);
        font.draw(myGdxGame.batch, "SCORE: " + score, 750, 530);

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
        restartButton.dispose();
        font.dispose();
        goToMenuButton.dispose();
    }
}
