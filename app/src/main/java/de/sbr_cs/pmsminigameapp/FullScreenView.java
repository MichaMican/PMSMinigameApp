package de.sbr_cs.pmsminigameapp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.sbr_cs.pmsminigameapp.Interface.Drawable;
import de.sbr_cs.pmsminigameapp.Interface.ResizeListener;

/**
 * View on which the game is displayed
 */
public class FullScreenView extends View {

    private int viewWidth;
    private int viewHeight;
    private ResizeListener resizeListener;
    private List<Drawable> drawables;

    /**
     * Standart CoinGameView constructor
     * @param context App context
     * @param attrs View attributes
     */
    public FullScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawables = new ArrayList<>();
    }

    /**
     * Register for resize events
     * @param resizeListener object that should receive resize updates
     */
    public void setResizeListener(ResizeListener resizeListener){
        this.resizeListener = resizeListener;
    }

    /**
     * Add drawable to canvas
     * @param d drawable that should appear on canvas
     */
    public void registerDrawable(Drawable d){
        drawables.add(d);
    }

    /**
     * Remove drawable from canvas
     * @param d drawable that should no longer appear on canvas
     */
    public void unregisterDrawable(Drawable d){
        drawables.remove(d);
    }

    /**
     * Notifies subscriber about resolution update
     * @param w New width in px
     * @param h New height in  px
     * @param oldw Old width in px
     * @param oldh Old height in px
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        if (resizeListener != null){
            resizeListener.onResize(w, h);
        }
    }

    /**
     * Draws all registered drawables to canvas
     * @param canvas canvas the drawables should be drawn on
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (Drawable d : drawables) {
            d.draw(canvas);
        }
    }

    /**
     * Reset view. Unregisters all registered drawables
     */
    public void reset() {
        drawables = new ArrayList<>();
    }

    public void hide() {
        setVisibility(INVISIBLE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }
}
