package ru.samsung.gamestudio.components.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.samsung.gamestudio.MyGdxGame;

public class MovingBackground {
    private Texture texture;
    private TextureRegion region;
    private static final float MOVE_SPEED = 45f;
    private float x1;
    private float x2;

    private float speed;

    public MovingBackground(Texture texture) {
        this.texture = texture;
        this.texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        this.region = new TextureRegion(
                texture,
                2,
                0,
                texture.getWidth() - 4,
                texture.getHeight()
        );

        this.x1 = 0;
        this.x2 = MyGdxGame.SCR_WIDTH - 1;

        speed = MOVE_SPEED;
    }
    public void move(float delta) {
        x1 -= speed * delta;
        x2 -= speed * delta;

        if (x1 <= -MyGdxGame.SCR_WIDTH) {
            x1 = x2 + MyGdxGame.SCR_WIDTH - 1;
        }
        if (x2 <= -MyGdxGame.SCR_WIDTH) {
            x2 = x1 + MyGdxGame.SCR_WIDTH - 1;
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void draw(Batch batch) {
        float paddingY = 50f;

        batch.draw(region, x1, -paddingY, MyGdxGame.SCR_WIDTH + 2, MyGdxGame.SCR_HEIGHT + (paddingY * 2));
        batch.draw(region, x2, -paddingY, MyGdxGame.SCR_WIDTH + 2, MyGdxGame.SCR_HEIGHT + (paddingY * 2));
    }

    public void dispose() {
        texture.dispose();
    }

    public void stop() {
        speed = 0;
    }
    public void start() {
        speed = MOVE_SPEED;
    }
}
