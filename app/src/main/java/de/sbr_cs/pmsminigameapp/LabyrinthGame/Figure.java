package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import de.sbr_cs.pmsminigameapp.Interface.Drawable;

public class Figure implements Drawable {

    Point position;
    int maxX;
    int maxY;
    double scale;

    public Figure(Point position, int maxX, int maxY, double scale) {
        this.position = position;
        this.maxX = maxX;
        this.maxY = maxY;
        this.scale = scale;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        draw(canvas, paint);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle((float) (position.getXpx(maxX) * scale),
                (float) (position.getYpx(maxY) * scale),
                (float) (LabyrinthGameManager.FIGURE_RADIUS * scale),
                paint);
    }
}
