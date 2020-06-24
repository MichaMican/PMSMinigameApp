package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.Nullable;
import de.sbr_cs.pmsminigameapp.Interface.Drawable;

public class LabyrinthPath implements Drawable {
    private Point startPoint;
    private Point endPoint;
    private Direction dir;
    private double scale;
    private int maxX;
    private int maxY;
    private Paint paint;
    private boolean isMovedOver;

    public LabyrinthPath(Point startPoint, Point endPoint, double scale, int maxX, int maxY) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.scale = scale;
        this.maxX = maxX;
        this.maxY = maxY;
        isMovedOver = false;
        this.paint = new Paint();
        this.paint.setColor(Color.BLUE);
        dir = calculateDirection();
    }

    private Direction calculateDirection() {
        if (startPoint.getX() > endPoint.getX()) {
            return Direction.LEFT;
        } else if (startPoint.getX() < endPoint.getX()) {
            return Direction.RIGHT;
        } else if (startPoint.getY() > endPoint.getY()) {
            //The coor sys origin is in the top left
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public boolean isValidMove(Point startPoint, Point endPoint){
        return (this.startPoint.equals(startPoint) && this.endPoint.equals(endPoint)) ||
                (this.startPoint.equals(endPoint) && this.endPoint.equals(startPoint));
    }

    @Override
    public void draw(Canvas canvas) {
        if(isMovedOver){
            paint.setColor(Color.GREEN);
        }
        draw(canvas, this.paint);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine((float) (startPoint.getXpx(maxX) * scale),
                (float) (startPoint.getYpx(maxY) * scale),
                (float) (endPoint.getXpx(maxX) * scale),
                (float) (endPoint.getYpx(maxY) * scale),
                paint);
        canvas.drawCircle((float) (startPoint.getXpx(maxX) * scale),
                (float) (startPoint.getYpx(maxY) * scale),
                (float) (LabyrinthGameManager.POINT_RADIUS * scale),
                paint);
        canvas.drawCircle((float) (endPoint.getXpx(maxX) * scale),
                (float) (endPoint.getYpx(maxY) * scale),
                (float) (LabyrinthGameManager.POINT_RADIUS * scale),
                paint);
    }

    public void setMovedOver() {
        isMovedOver=true;
    }

    public boolean isMovedOver() {
        return isMovedOver;
    }
}
