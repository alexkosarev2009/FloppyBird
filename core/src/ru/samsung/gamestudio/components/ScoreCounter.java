package ru.samsung.gamestudio.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScoreCounter {

    private int x, y;
    private BitmapFont font;
    public ScoreCounter(int x, int y) {
        this.x = x;
        this.y = y;

        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ArialHB.ttc"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        parameter.color = Color.WHITE;

        parameter.borderWidth = 3f;
        parameter.borderColor = Color.BLACK;

        font = freeTypeFontGenerator.generateFont(parameter);
        freeTypeFontGenerator.dispose();
    }

    public void draw(Batch batch, long score) {
        font.draw(batch, String.valueOf(score), x, y);
    }

    public void dispose() {
        font.dispose();
    }
}