package de.sbr_cs.pmsminigameapp.CoinGame;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoinGameManager implements ResizeListener {

    private static final float MAX_VELOCITY = 10;
    private static final float BALL_RADIUS = 20;
    private static final float COIN_RADIUS = 10;
    private static final float KILL_CIRCLE_MAX_RADIUS = 40;
    private static final int SPAWNTIME = 10;

    private Ball ball;
    private CoinGameActivity coinGameActivity;
    private List<Coin> coins;
    private List<KillerCircle> killerCircles;
    private CoinGameView coinGameView;
    private float canvasWidth;
    private float canvasHeight;
    private float scale;
    private int stepCounter;
    private int score;
    private boolean isRunning;

    public CoinGameManager(CoinGameActivity coinGameActivity, CoinGameView coinGameView) {
        this.coinGameView = coinGameView;
        this.coinGameActivity = coinGameActivity;

        reset();
    }

    private void reset() {
        coinGameView.reset();

        ball = new Ball(50, 50, BALL_RADIUS, 0, 0);
        coins = new ArrayList<>();
        killerCircles = new ArrayList<>();
        score = 0;

        coinGameView.registerDrawable(ball);
        coinGameView.setResizeListener(this);
        canvasHeight = 100;
        canvasWidth = 100;

        scale = 1;
        isRunning = true;
    }

    //simulates a step
    public void step() {
        if (isRunning) {
            stepCounter++;
            if (stepCounter > SPAWNTIME * 100) {
                stepCounter = 0;
                spawnCoin((int) Math.ceil(Math.random() * 3));
                spawnKillerCircle();
            }

            ball.move(canvasWidth, canvasHeight);

            if (!checkCollision(killerCircles).isEmpty()) {
                triggerGameover();
            }

            handleCoinCollision(checkCollision(coins));

            updateView();
        }
    }

    private <T extends Collidable> List<T> checkCollision(List<T> obstacles) {
        List<T> collidedObstacles = new ArrayList<>();

        for (T obstacle : obstacles) {
            if (obstacle.isColided(ball)) {
                collidedObstacles.add(obstacle);
            }
        }

        return collidedObstacles;
    }

    private void handleCoinCollision(List<Coin> coins) {
        for (Coin coin : coins) {
            coinGameView.unregisterDrawable(coin);
            coins.remove(coin);
            score++;
        }
    }

    private void triggerGameover() {
        isRunning = false;
        coinGameActivity.triggerGameOver(score);
        reset();
    }

    public void sensorUpdate(float x, float y) {
        if(isRunning){
            ball.accelerate(-(scale * (x / 9.81f)), scale * (y / 9.81f));
        }
    }

    public void updateView() {
        coinGameView.postInvalidate();
    }

    private void spawnCoin(int numberOfCoins) {
        for (int i = 0; i < numberOfCoins; i++) {
            Coin newCoin = new Coin((float) (Math.random() * canvasWidth), (float) (Math.random() * canvasHeight), scale * COIN_RADIUS);
            coins.add(newCoin);
            coinGameView.registerDrawable(newCoin);
        }
    }

    private void spawnKillerCircle() {
        KillerCircle newKillerCircle = new KillerCircle((float) (Math.random() * canvasWidth), (float) (Math.random() * canvasHeight), (float) (scale * KILL_CIRCLE_MAX_RADIUS * Math.random()));
        killerCircles.add(newKillerCircle);
        coinGameView.registerDrawable(newKillerCircle);
    }

    @Override
    public void onResize(float w, float h) {
        if(isRunning) {
            canvasWidth = w;
            canvasHeight = h;
            //480 * 800 is testdevise size
            scale = canvasHeight / 720;

            ball.setRadius(BALL_RADIUS * scale);
            ball.setMaxVelocity(MAX_VELOCITY * scale);
        }
    }
}
