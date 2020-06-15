package de.sbr_cs.pmsminigameapp.CoinGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CoinGameView extends View {
    private Paint ballPaint = new Paint();
    private Paint brickPaint = new Paint();
    private Paint coinPaint = new Paint();

    int viewWidth;
    int viewHeight;
    int posx;
    int posy;

    public CoinGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ballPaint.setAntiAlias(true);
        ballPaint.setStrokeWidth(6f);
        ballPaint.setColor(Color.BLACK);
        ballPaint.setStyle(Paint.Style.STROKE);
        ballPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(posx, posy, 20, ballPaint);
    }

    public void move(int dx, int dy) {
        posx = posx + dx;
        posy = posy + dy;
        invalidate();
        if (posx > viewWidth) {
            posx = 0;
        } else if (posy > viewHeight) {
            posy = 0;
        } else if (posx < 0) {
            posx = viewWidth;
        } else if (posy < 0) {
            posy = viewHeight;
        }
    }
}
