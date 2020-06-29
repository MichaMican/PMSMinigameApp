package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import de.sbr_cs.pmsminigameapp.Interface.Drawable;

/**
 * Figure which the user has to navigate through the Maze
 */
public class Figure implements Drawable {

    Point position;
    int maxX;
    int maxY;
    double scale;

    /**
     * Standard constructor
     * @param position Start position of figure
     * @param maxX Grids X max value
     * @param maxY Grids Y max value
     * @param scale Scale of ui
     */
    public Figure(Point position, int maxX, int maxY, double scale) {
        this.position = position;
        this.maxX = maxX;
        this.maxY = maxY;
        this.scale = scale;
    }

    /**
     * Sets the position of the figure
     * @param position New Position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Sets the scale in which the Figure shall be displayed
     * @param scale New scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Draws the Figure to canvas in the default color
     * @param canvas Canvas on which the object should be drawn
     */
    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        draw(canvas, paint);
    }

    /**
     * Draws the Figure to canvas with a specified color
     * @param canvas
     * @param paint
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle((float) (position.getXpx(maxX) * scale),
                (float) (position.getYpx(maxY) * scale),
                (float) (LabyrinthGameManager.FIGURE_RADIUS * scale),
                paint);
    }
}
