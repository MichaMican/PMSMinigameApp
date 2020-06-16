package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class Obstacle implements Drawable, Collidable {
    protected float x;
    protected float y;
    protected float radius;
    protected Paint paint;


    public Obstacle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public boolean isColided(Ball ball) {

        float xDist = Math.abs(x - ball.getX());
        float yDist = Math.abs(y - ball.getY());

        float distanceBetweenPoints = (float) Math.sqrt(xDist * xDist + yDist * yDist);

        return distanceBetweenPoints <= ball.getRadius() + radius;
    }
}
