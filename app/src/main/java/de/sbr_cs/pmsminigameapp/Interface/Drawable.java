package de.sbr_cs.pmsminigameapp.Interface;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Interface for objects that can be drawn on the canvas
 */
public interface Drawable {
    /**
     * Draws the object on the provided canvas with standard color
     * @param canvas Canvas on which the object should be drawn
     */
    void draw(Canvas canvas);

    /**
     * Draws the object on the provided canvas with specified paint
     * @param canvas Canvas on which the object should be drawn
     * @param paint Paint the object should be displayed with
     */
    void draw(Canvas canvas, Paint paint);
}
