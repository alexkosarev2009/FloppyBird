package ru.samsung.gamestudio;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameScreen implements Screen {
    private final MyGdxGame myGdxGame;

    private final Random random = new Random();

    private boolean isGameOver;

    private final MovingBackground movingBackground;

    private final ScoreCounter scoreCounter;
    private long score;

    private final Bird bird;

    private final int TUBE_COUNT = 5;
    private Tube[] tubes;


    private float shakeTime = 0f;
    private float shakeIntensity = 0f;
    private final float NORMAL_SHAKE_INTENSITY = 13f;
    private final float startCameraX;
    private final float startCameraY;


    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        movingBackground = new MovingBackground(new Texture("backgrounds/game_bg.png"));
        bird = new Bird((float) SCR_WIDTH / 2 - Bird.WIDTH, 300, new Texture("birdTiles/bird0.png"), 150, 0);

        initTubes();

        this.startCameraX = myGdxGame.camera.position.x;
        this.startCameraY = myGdxGame.camera.position.y;

        final int pointCounterMarginTop = 60 ;
        final int pointCounterMarginRight = 60;
        scoreCounter = new ScoreCounter(pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
    }

    public void startShake(float duration, float intensity) {
        this.shakeTime = duration;
        this.shakeIntensity = intensity;
    }

    @Override
    public void render(float delta) {
        if (shakeTime > 0) {
            shakeTime -= delta;

            float currentIntensity = shakeIntensity * (shakeTime / 0.4f);

            myGdxGame.camera.position.x = startCameraX + (random.nextFloat() - 0.5f) * 2f * currentIntensity;
            myGdxGame.camera.position.y = startCameraY + (random.nextFloat() - 0.5f) * 2f * currentIntensity;
        } else {
            myGdxGame.camera.position.x = startCameraX;
            myGdxGame.camera.position.y = startCameraY;
        }

        if (isGameOver) {
            for (Tube tube: tubes) {
                tube.stop();
            }
            movingBackground.stop();
        }
        if (Gdx.input.justTouched() && !isGameOver) {
            bird.jump();
        }
        if (!bird.isInBounds()) {
            if (!isGameOver) startShake(0.4f, NORMAL_SHAKE_INTENSITY);
            isGameOver = true;
        }

        movingBackground.move(delta);

        bird.fly(delta);
        bird.changeTile(delta);

        for (Tube tube : tubes) {
            tube.move(delta);
            if (tube.isHitLowerTube(bird)) {
                if (!isGameOver) startShake(0.4f, NORMAL_SHAKE_INTENSITY);
                isGameOver = true;
            }
            if (tube.isHitUpperTube(bird)) {
                if (!isGameOver) startShake(0.4f, NORMAL_SHAKE_INTENSITY);
                isGameOver = true;
                bird.hitUpperTube();
            }
            if (tube.isIncreasingScoreNeeded(bird)) {
                score++;
            }
        }

        ScreenUtils.clear(0.3058f, 0.5725f, 0.7882f, 1f);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        movingBackground.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        scoreCounter.draw(myGdxGame.batch, score);
        myGdxGame.batch.end();
    }

    @Override
    public void show() {
        isGameOver = false;
        score = 0;
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
        bird.dispose();
        for (Tube tube: tubes) {
            tube.dispose();
        }
        scoreCounter.dispose();
        movingBackground.dispose();
    }

    public void initTubes() {
        tubes = new Tube[TUBE_COUNT];
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes[i] = new Tube(random, TUBE_COUNT, i);
        }
    }
}
