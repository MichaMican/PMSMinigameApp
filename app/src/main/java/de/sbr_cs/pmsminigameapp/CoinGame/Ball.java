package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball implements Drawable {


    private float x;
    private float y;
    private float radius;
    private float vx;
    private float vy;
    private Paint ballPaint = new Paint();
    private float maxVelocity = 0;


    public Ball(float x, float y, float radius, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public void changeVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void accelerate(float ax, float ay){
        vx += ax;
        vy += ay;


    }

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

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, ballPaint);
    }
}
