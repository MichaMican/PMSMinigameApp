package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Canvas;
import android.graphics.Paint;

import de.sbr_cs.pmsminigameapp.CoinGame.Interface.Drawable;

/**
 * Ball the player plays with in the coin game
 */
public class Ball implements Drawable {

    private float x;
    private float y;
    private float radius;
    private float vx;
    private float vy;
    private Paint ballPaint = new Paint();
    private float maxVelocity = 0;

    /**
     * Standard ball constructor
     * @param x Start x Position
     * @param y Start x Position
     * @param radius Radius of ball in px
     * @param vx Start x velocity
     * @param vy Start y velocity
     */
    public Ball(float x, float y, float radius, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * X coordinate getter
     * @return x Coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Y coordinate getter
     * @return y Coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Radius getter
     * @return Radius in px
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets Radius of ball
     * @param radius New radius of ball in px
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Sets max velocity the ball can reach in each dimension
     * @param maxVelocity New max velocity
     */
    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    /**
     * Sets x & y velocity
     * @param vx New x velocity
     * @param vy New y velocity
     */
    public void changeVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Accelerates the ball in x & y dimension
     * @param ax X acceleration
     * @param ay Y acceleration
     */
    public void accelerate(float ax, float ay){
        vx += ax;
        vy += ay;
    }

    /**
     * Moves the ball one step further
     * @param xMax Max x value after which the ball clips to the other side
     * @param yMax Max y value after which the ball clips to the other side
     */
    public void move(float xMax, float yMax) {
        x += vx;
        y += vy;

        //Ball appears on the other side if he leaves the monitor dimensions
        if(x < 0){
            x += xMax;
        } else if (x > xMax){
            x -= xMax;
        }
        if(y < 0){
            y += yMax;
        } else if (y > yMax){
            y -= yMax;
        }

        //make sure the ball doesn't get to fast
        if(vx > maxVelocity){
            vx = maxVelocity;
        } else if(vx < -maxVelocity){
            vx = -maxVelocity;
        }
        if(vy > maxVelocity){
            vy = maxVelocity;
        } else if(vy < -maxVelocity){
            vy = -maxVelocity;
        }
    }

    /**
     * Draw ball on canvas
     * @param canvas Canvas on which the object should be drawn
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, ballPaint);
    }
}
