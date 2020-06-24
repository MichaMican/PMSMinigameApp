package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Canvas;
import android.graphics.Paint;

import de.sbr_cs.pmsminigameapp.Interface.Collidable;
import de.sbr_cs.pmsminigameapp.Interface.Drawable;

/**
 * Circular obstacle that can spawn in the coin game
 */
abstract class Obstacle implements Drawable, Collidable {
    /**
     * X coordinate of obstacle
     */
    protected float x;
    /**
     * Y coordinate of obstacle
     */
    protected float y;
    /**
     * Radius of obstacle
     */
    protected float radius;
    /**
     * Paint with which the obstacle should be drawn on the canvas
     */
    protected Paint paint;

    /**
     * Standard obstacle constructor
     * @param x Start x coordinate of obstacle
     * @param y Start y coordinate of obstacle
     * @param radius Radius of obstacle
     */
    public Obstacle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = new Paint();
    }

    /**
     * Draws circle on canvas with style defined by paint
     * @param canvas Canvas on which the object should be drawn
     */
    @Override
    public void draw(Canvas canvas) {
        draw(canvas, this.paint);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, radius, paint);
    }

    /**
     * Calculates if ball collides with the obstacle
     * @param ball Ball whos collision should be checked
     * @return Boolean value if the ball collides with the obstacle
     */
    @Override
    public boolean isColided(Ball ball) {

        float xDist = Math.abs(x - ball.getX());
        float yDist = Math.abs(y - ball.getY());

        float distanceBetweenPoints = (float) Math.sqrt(xDist * xDist + yDist * yDist);

        return distanceBetweenPoints <= ball.getRadius() + radius;
    }
}
