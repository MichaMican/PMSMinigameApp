package de.sbr_cs.pmsminigameapp.CoinGame.Interface;

import de.sbr_cs.pmsminigameapp.CoinGame.Ball;

/**
 * Interface for objects that the ball can collided with
 */
public interface Collidable {
    /**
     * Checks if ball collided with the collidable object
     * @param ball Ball whos collision should be checked
     * @return Boolean value if the provided ball collides with the object
     */
    boolean isColided(Ball ball);
}
