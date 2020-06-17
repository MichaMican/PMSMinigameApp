package de.sbr_cs.pmsminigameapp.CoinGame.Interface;

import android.graphics.Canvas;

/**
 * Interface for objects that can be drawn on the canvas
 */
public interface Drawable {
    /**
     * Draws the object on the provided canvas
     * @param canvas Canvas on which the object should be drawn
     */
    void draw(Canvas canvas);
}
