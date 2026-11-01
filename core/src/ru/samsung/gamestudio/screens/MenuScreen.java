package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.components.TextButton;

public class MenuScreen implements Screen {

    public MyGdxGame myGdxGame;

    private MovingBackground movingBackground;

    private final TextButton startGameButton;
    private final TextButton quitGameButton;


    public MenuScreen(MyGdxGame myGdxGame) {
        this.movingBackground = new MovingBackground(new Texture("backgrounds/game_bg.png"));
        this.myGdxGame = myGdxGame;

        this.startGameButton = new TextButton(MyGdxGame.SCR_WIDTH / 2, 500, "Start");
        this.quitGameButton = new TextButton(MyGdxGame.SCR_WIDTH / 2, 300, "Quit");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        movingBackground.move(delta);

        Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        if (startGameButton.isClicked(touch.x, touch.y)) {
            myGdxGame.gameScreen = new GameScreen(myGdxGame);
            myGdxGame.setScreen(myGdxGame.gameScreen);
        }
        if (quitGameButton.isClicked(touch.x, touch.y)) {
            Gdx.app.exit();
        }

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        startGameButton.draw(myGdxGame.batch);
        quitGameButton.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
    }
}
