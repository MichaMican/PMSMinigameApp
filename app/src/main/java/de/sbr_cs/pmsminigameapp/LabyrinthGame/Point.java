package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;

import androidx.annotation.Nullable;
import de.sbr_cs.pmsminigameapp.Interface.Drawable;

public class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * gets X Pixel coordinate - Doesn't not apply scale!
     * @param maxX
     * @return X Pixel coordinate
     */
    public float getXpx(int maxX){
        return (((float)x/(float)maxX) * (LabyrinthGameManager.STANDARD_WIDTH - 20) + 10);
    }

    public float getYpx(int maxY){
        return (((float)y/(float)maxY) * (LabyrinthGameManager.STANDARD_HEIGHT - 20) + 10);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Point)){
            return false;
        }
        Point point = ((Point) obj);
        return point.getX() == x && point.getY() == y;
    }
}
