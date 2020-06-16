package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class KillerCircle extends Obstacle {

    public KillerCircle(float x, float y, float radius) {
        super(x, y, radius);
        paint.setColor(Color.RED);
    }
}
