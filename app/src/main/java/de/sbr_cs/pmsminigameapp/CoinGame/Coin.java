package de.sbr_cs.pmsminigameapp.CoinGame;

import android.graphics.Color;

import de.sbr_cs.pmsminigameapp.CoinGame.Obstacle;

/**
 * Coin that can be collected in the Coin game
 */
public class Coin extends Obstacle {

    private boolean counted;

    /**
     * Standard constructor
     * @param x X Coordinate
     * @param y Y Coordinate
     * @param radius Radius in px
     */
    public Coin(float x, float y, float radius) {
        super(x, y, radius);
        paint.setColor(Color.YELLOW);
        counted = false;
    }

    /**
     * Returns value if the coin has already been counted to the score
     * @return Boolean value if the coin was already counted
     */
    public boolean isCounted() {
        return counted;
    }

    /**
     * Sets the counted value to true
     */
    public void setCounted() {
        this.counted = true;
    }
}
