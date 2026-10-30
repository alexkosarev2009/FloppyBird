package ru.samsung.gamestudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;

public class ScoreCounter {

    int x, y;
    BitmapFont font;
    ScoreCounter(int x, int y) {
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

    void draw(Batch batch, long score) {
        font.draw(batch, String.valueOf(score), x, y);
    }

    void dispose() {
        font.dispose();
    }
}