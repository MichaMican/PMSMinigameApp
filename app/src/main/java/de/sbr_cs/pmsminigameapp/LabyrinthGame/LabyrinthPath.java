package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.Nullable;
import de.sbr_cs.pmsminigameapp.Interface.Drawable;

/**
 * Labyrinth path object
 */
public class LabyrinthPath implements Drawable {
    private Point startPoint;
    private Point endPoint;
    private double scale;
    private int maxX;
    private int maxY;
    private Paint paint;
    private boolean isMovedOver;

    /**
     * Standard constructor
     * @param startPoint Start point of path
     * @param endPoint End point of path
     * @param scale Scale
     * @param maxX Labyrinth grid XMax
     * @param maxY Labyrinth grid YMax
     */
    public LabyrinthPath(Point startPoint, Point endPoint, double scale, int maxX, int maxY) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.scale = scale;
        this.maxX = maxX;
        this.maxY = maxY;
        isMovedOver = false;
        this.paint = new Paint();
        this.paint.setColor(Color.BLUE);
    }

    /**
     * Sets scale with which the labyrinth path shall be displayed
     * @param scale New scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Sets move over to true
     */
    public void setMovedOver() {
        isMovedOver=true;
    }

    /**
     * Checks if figure already moved over/visited the path
     * @return if path was visited
     */
    public boolean isMovedOver() {
        return isMovedOver;
    }
    /**
     * Checks if a move is a valid move for this path tile
     * @param startPoint Start point of the move
     * @param endPoint End point of the move
     * @return if the move was valid on this path
     */
    public boolean isValidMove(Point startPoint, Point endPoint){
        return (this.startPoint.equals(startPoint) && this.endPoint.equals(endPoint)) ||
                (this.startPoint.equals(endPoint) && this.endPoint.equals(startPoint));
    }

    /**
     * Draws path the canvas with blue or green color (depending of if figure visited the path already)
     * @param canvas Canvas on which the object should be drawn
     */
    @Override
    public void draw(Canvas canvas) {
        if(isMovedOver){
            paint.setColor(Color.GREEN);
        }
        draw(canvas, this.paint);
    }

    /**
     * Draws path the canvas with provided paint
     * @param canvas Canvas on which the object should be drawn
     * @param paint Paint the object should be displayed with
     */
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


}
