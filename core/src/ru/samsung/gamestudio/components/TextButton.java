package ru.samsung.gamestudio.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextButton {
    private BitmapFont bitmapFont;

    private String text;
    private Texture texture;

    private int x, y;
    private int textX, textY;
    public static int BUTTON_WIDTH, BUTTON_HEIGHT;
    public static int TEXT_WIDTH, TEXT_HEIGHT;

    public TextButton(int x, int y, String text) {
        this.text = text;
        this.x = x;
        this.y = y;

        bitmapFont = new BitmapFont();
        bitmapFont.getData().scale(5f);
        bitmapFont.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(bitmapFont, text);
        TEXT_WIDTH = (int) gl.width;
        TEXT_HEIGHT = (int) gl.height;

        texture = new Texture("backgrounds/button_bg.png");
        BUTTON_WIDTH = texture.getWidth();
        BUTTON_HEIGHT = texture.getHeight();

        textX = x + (BUTTON_WIDTH - TEXT_WIDTH) / 2;
        textY = y + (BUTTON_HEIGHT + TEXT_HEIGHT) / 2;
    }

    public void draw(Batch batch) {
        batch.draw(texture, x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        bitmapFont.draw(batch, text, textX, textY);
    }
    public void dispose() {
        texture.dispose();
        bitmapFont.dispose();
    }

    public boolean isClicked(float tx, float ty) {
        return tx >= x && tx <= x + BUTTON_WIDTH && ty >= y && ty <= y + BUTTON_HEIGHT;
    }

}
