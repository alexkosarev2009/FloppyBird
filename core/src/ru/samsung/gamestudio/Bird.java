package ru.samsung.gamestudio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bird {
    private float x, y;
    private final float paddingX = 12f;
    private final float paddingY = 10f;

    private Rectangle bounds;

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    private TextureRegion currentTexture;

    private float rotation = 0f;
    public static final float WIDTH = 100f;
    public static final float HEIGHT = 80f;
    private final TextureRegion[] framesArray = new TextureRegion[]{
            new TextureRegion(new Texture("birdTiles/bird0.png")),
            new TextureRegion(new Texture("birdTiles/bird1.png")),
            new TextureRegion(new Texture("birdTiles/bird2.png")),
            new TextureRegion(new Texture("birdTiles/bird1.png")),
    };
    private float animationTimer;
    private final float TIME_PER_FRAME = 0.1f;
    private int speedX;
    private float speedY;
    private final float GRAVITY = -1200f;
    private final float JUMP_IMPULSE = 420f;
    private final float MAX_FALL_SPEED = -700f;

    public Bird(float x, float y, Texture texture, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.animationTimer = 0f;
        this.currentTexture = new TextureRegion(texture);
        this.speedY = speedY;
        this.speedX = speedX;
        this.bounds = new Rectangle(x + paddingX,
                y + paddingY,
                WIDTH - (paddingX * 2),
                HEIGHT - (paddingY * 2));
    }

    public void fly(float delta) {
        speedY += GRAVITY * delta;

        if (speedY < MAX_FALL_SPEED) {
            speedY = MAX_FALL_SPEED;
        }

        y += speedY * delta;

        bounds.setPosition(x + paddingX, y + paddingY);


        float targetRotation;
        if (speedY > 0) {
            targetRotation = 35f;
            rotation = MathUtils.lerp(rotation, targetRotation, 15f * delta);
        }
        else {
            targetRotation = -70f;
            rotation = MathUtils.lerp(rotation, targetRotation, 2f * delta);
        }


        if (y < 0) {
            y = 0;
            speedY = 0;
        }
    }

    public void jump() {
        speedY = JUMP_IMPULSE;
    }

    public void draw(Batch batch) {
        batch.draw(
                currentTexture,
                x,
                y,
                WIDTH / 2f,
                HEIGHT / 2f,
                WIDTH,
                HEIGHT,
                1f,
                1f,
                rotation);
    }
    public void dispose() {
        for (TextureRegion t : framesArray) {
            t.getTexture().dispose();
        }
    }

    public void changeTile(float delta) {
        animationTimer += delta;
        int frameIndex = (int) (animationTimer / TIME_PER_FRAME) % 4;
        currentTexture = framesArray[frameIndex];
    }
    public boolean isInBounds() {
        return y > 0 && (y + HEIGHT) <= MyGdxGame.SCR_HEIGHT;
    }

    public void hitUpperTube() {
        speedY = -JUMP_IMPULSE;
    }


}
