package de.sbr_cs.pmsminigameapp.CoinGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CoinGameView extends View {

    private int viewWidth;
    private int viewHeight;
    private ResizeListener resizeListener;
    private List<Drawable> drawables;

    public CoinGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        drawables = new ArrayList<>();
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setResizeListener(ResizeListener resizeListener){
        this.resizeListener = resizeListener;
    }

    public void registerDrawable(Drawable d){
        drawables.add(d);
    }

    public void unregisterDrawable(Drawable d){
        drawables.remove(d);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        if (resizeListener != null){
            resizeListener.onResize(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Drawable d : drawables) {
            d.draw(canvas);
        }
    }

    public void reset() {
        drawables = new ArrayList<>();
    }
}
