package ru.samsung.gamestudio;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Tube {
    private TextureRegion upperTubeTexture;
    private TextureRegion lowerTubeTexture;

    private final Random random;

    public static final float WIDTH = 120f;
    public static final float HEIGHT = 600f;
    private float x;
    private float distanceBetweenTubes;
    private int padding = 50;
    private float speed = 200f;

    private int gapY;

    private final int GAP_HEIGHT = 250;

    private boolean isPointReceived;

    public Tube(Random random, int tubeCount, int tubeIndex) {
        upperTubeTexture = new TextureRegion(new Texture("tubes/tube_flipped.png"));
        lowerTubeTexture = new TextureRegion(new Texture("tubes/tube.png"));
        upperTubeTexture.flip(true, false);

        distanceBetweenTubes = (SCR_WIDTH + WIDTH) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIndex + SCR_WIDTH;
        this.isPointReceived = false;

        this.random = random;
        this.gapY = GAP_HEIGHT / 2 + padding + this.random.nextInt(SCR_HEIGHT - 2 * (padding + GAP_HEIGHT / 2));
    }

    public void draw(Batch batch) {
        batch.draw(upperTubeTexture, x, gapY + GAP_HEIGHT / 2, WIDTH, HEIGHT);
        batch.draw(lowerTubeTexture, x, gapY - GAP_HEIGHT / 2 - HEIGHT, WIDTH, HEIGHT);
    }

    public void dispose() {
        lowerTubeTexture.getTexture().dispose();
        upperTubeTexture.getTexture().dispose();
    }
    void move(float delta) {
        x -= speed * delta;
        if (x < -WIDTH) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = GAP_HEIGHT / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + GAP_HEIGHT / 2));
        }
    }
    public void stop() {
        speed = 0;
    }

    public boolean isHitLowerTube(Bird bird) {
        Rectangle birdBounds = bird.getBounds();

        if (birdBounds.getX() + birdBounds.width >= x && birdBounds.getX() <= x + WIDTH) {
            return birdBounds.getY() <= gapY - (float) GAP_HEIGHT / 2;
        }
        return false;

    }
    public boolean isHitUpperTube(Bird bird) {
        Rectangle birdBounds = bird.getBounds();
        if (birdBounds.getX() + birdBounds.width >= x && birdBounds.getX() <= x + WIDTH) {
            return birdBounds.getY() + birdBounds.height >= gapY + (float) GAP_HEIGHT / 2;
        }
        return false;
    }

    public boolean isIncreasingScoreNeeded(Bird bird) {
        if (isPointReceived || bird.getBounds().x + Bird.WIDTH < x + WIDTH / 2) return false;
        isPointReceived = true;
        return true;
    }
}
