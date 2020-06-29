package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import android.graphics.Canvas;

import androidx.annotation.Nullable;
import de.sbr_cs.pmsminigameapp.Interface.Drawable;

/**
 * Point object
 */
public class Point{
    private int x;
    private int y;

    /**
     * Standard constructor
     * @param x
     * @param y
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * gets X Coordinate of point
     * @return X Coordinate of point
     */
    public int getX() {
        return x;
    }

    /**
     * gets Y Coordinate of point
     * @return Y Coordinate of point
     */
    public int getY() {
        return y;
    }

    /**
     * gets X Pixel coordinate - Does not apply scale!
     * @param maxX Labyrinth grid XMax
     * @return X Pixel coordinate
     */
    public float getXpx(int maxX){
        return (((float)x/(float)maxX) * (LabyrinthGameManager.STANDARD_WIDTH - 20) + 10);
    }

    /**
     * gets Y Pixel coordinate - Does not apply scale!
     * @param maxY Labyrinth grid YMax
     * @return Y Pixel coordinate
     */
    public float getYpx(int maxY){
        return (((float)y/(float)maxY) * (LabyrinthGameManager.STANDARD_HEIGHT - 20) + 10);
    }

    /**
     * Checks if object equals to this point
     * @param obj object that shall be compared
     * @return if this and object is the equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Point)){
            return false;
        }
        Point point = ((Point) obj);
        return point.getX() == x && point.getY() == y;
    }
}
