package ru.samsung.gamestudio.components.tubes;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.characters.Bird;

public class ShiftingTube extends Tube {

    private static final float SHIFT_AMOUNT = 200f;

    private float startGapY;
    private float targetGapY;
    public boolean isShiftStarted = false;

    private float shiftTime = 0;
    private float shiftDuration = 0;

    public ShiftingTube(Random random, int tubeCount, int tubeIndex) {
        super(random, tubeCount, tubeIndex);

        startGapY = gapY;

        if (gapY < MyGdxGame.SCR_HEIGHT / 2f) {
            targetGapY = gapY + SHIFT_AMOUNT;
        } else {
            targetGapY = gapY - SHIFT_AMOUNT;
        }
    }

    public void startShifting(float duration) {
        if (!isShiftStarted && random.nextInt(5) == 0) {
            shiftDuration = duration;
            isShiftStarted = true;
        }
    }

    public void shift(float delta) {
        if (shiftTime < shiftDuration) {

            shiftTime += delta;

            float progress = Math.min(1f, shiftTime / shiftDuration);
            float alpha = Interpolation.bounceOut.apply(progress);

            gapY = startGapY + (targetGapY - startGapY) * alpha;
        } else isShiftStarted = false;
    }

    public boolean doStartShift(Bird bird) {
        Rectangle birdBounds = bird.getBounds();

        float distanceToBird = x - birdBounds.getX() + birdBounds.getWidth();

        return distanceToBird <= distanceBetweenTubes;
    }

    @Override
    public void move(float delta) {
        x -= speed * delta;
        if (x < -WIDTH) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = (float) GAP_HEIGHT / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + GAP_HEIGHT / 2));

            startGapY = gapY;

            if (gapY < MyGdxGame.SCR_HEIGHT / 2f) {
                targetGapY = gapY + SHIFT_AMOUNT;
            } else {
                targetGapY = gapY - SHIFT_AMOUNT;
            }
            shiftTime = 0f;
            isShiftStarted = false;

        }
    }
}
