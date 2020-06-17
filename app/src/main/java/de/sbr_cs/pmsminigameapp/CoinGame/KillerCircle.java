package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Color;

import de.sbr_cs.pmsminigameapp.CoinGame.Obstacle;

/**
 * Killer circle which ends the game if the player collides with it
 */
public class KillerCircle extends Obstacle {

    /**
     * Standard KillerCircle constructor
     * @param x X coordinate
     * @param y Y coordinate
     * @param radius Radius in px
     */
    public KillerCircle(float x, float y, float radius) {
        super(x, y, radius);
        paint.setColor(Color.RED);
    }
}
