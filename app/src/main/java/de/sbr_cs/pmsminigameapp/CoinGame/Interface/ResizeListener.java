package de.sbr_cs.pmsminigameapp.CoinGame.Interface;

/**
 * Interface for resize event
 */
public interface ResizeListener {
    /**
     * gets called on resize
     * @param w width in px
     * @param h height in px
     */
    void onResize(float w, float h);
}
