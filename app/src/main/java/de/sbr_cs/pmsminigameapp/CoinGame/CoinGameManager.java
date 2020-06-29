package de.sbr_cs.pmsminigameapp.CoinGame;

import java.util.ArrayList;
import java.util.List;

import de.sbr_cs.pmsminigameapp.FullScreenView;
import de.sbr_cs.pmsminigameapp.Interface.Collidable;
import de.sbr_cs.pmsminigameapp.Interface.ResizeListener;

/**
 * Control class for the coin game
 */
public class CoinGameManager implements ResizeListener {

    private static final float MAX_VELOCITY = 10;
    private static final float BALL_RADIUS = 20;
    private static final float COIN_RADIUS = 10;
    private static final float KILL_CIRCLE_MAX_RADIUS = 40;
    private static final int SPAWN_TIME = 10;

    private Ball ball;
    private CoinGameActivity coinGameActivity;
    private List<Coin> coins;
    private List<KillerCircle> killerCircles;
    private FullScreenView fullScreenView;
    private float canvasWidth;
    private float canvasHeight;
    private float scale;
    private int stepCounter;
    private int score;
    private boolean isRunning;

    /**
     * Standard control class constructor
     * @param coinGameActivity Activity where the view is embedded
     * @param fullScreenView View where the canvas to draw on is located
     */
    public CoinGameManager(CoinGameActivity coinGameActivity, FullScreenView fullScreenView) {
        this.fullScreenView = fullScreenView;
        this.coinGameActivity = coinGameActivity;

        reset();
    }

    /**
     * Resets the game
     */
    private void reset() {
        fullScreenView.reset();

        ball = new Ball(50, 50, BALL_RADIUS, 0, 0);
        coins = new ArrayList<>();
        killerCircles = new ArrayList<>();
        score = 0;

        fullScreenView.registerDrawable(ball);
        fullScreenView.setResizeListener(this);
        canvasHeight = 100;
        canvasWidth = 100;

        scale = 1;
        isRunning = true;
    }

    /**
     * Checks & handles collisions, moves ball and draws current state to the view. This should get called at least 30 times per second, ideally at least 60 times per second
     */
    public void step() {
        if (isRunning) {
            stepCounter++;
            if (stepCounter > SPAWN_TIME * 100) {
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

        List<Coin> coinsCpy = new ArrayList<>(coins);

        for (Coin coin : coinsCpy) {
            fullScreenView.unregisterDrawable(coin);
            coins.remove(coin);
            if(!coin.isCounted()){
                coin.setCounted();
                score++;
            }
        }
    }

    private void triggerGameover() {
        isRunning = false;
        coinGameActivity.triggerGameOver(score);
        //reset();
    }

    /**
     * handles the acceleration sensor change
     * @param x X angle acceleration
     * @param y Y angle acceleration
     */
    public void sensorUpdate(float x, float y) {
        if(isRunning){
            ball.accelerate(-(scale * (x / 9.81f)), scale * (y / 9.81f));
        }
    }

    /**
     * Invalidates the current canvas so all drawable that are registered are redrawn
     */
    public void updateView() {
        fullScreenView.postInvalidate();
    }

    private void spawnCoin(int numberOfCoins) {
        for (int i = 0; i < numberOfCoins; i++) {
            Coin newCoin = new Coin((float) (Math.random() * canvasWidth), (float) (Math.random() * canvasHeight), scale * COIN_RADIUS);
            coins.add(newCoin);
            fullScreenView.registerDrawable(newCoin);
        }
    }

    private void spawnKillerCircle() {
        KillerCircle newKillerCircle = new KillerCircle((float) (Math.random() * canvasWidth), (float) (Math.random() * canvasHeight), (float) (scale * KILL_CIRCLE_MAX_RADIUS * Math.random()));
        killerCircles.add(newKillerCircle);
        fullScreenView.registerDrawable(newKillerCircle);
    }

    /**
     * Sets the scale according to dimensions, so the game feels similar across all device dimensions
     * @param w width in px
     * @param h height in px
     */
    @Override
    public void onResize(float w, float h) {
        if(isRunning) {
            canvasWidth = w;
            canvasHeight = h;

            //480 * 800 is test devise size
            scale = canvasHeight / 720;

            ball.setRadius(BALL_RADIUS * scale);
            ball.setMaxVelocity(MAX_VELOCITY * scale);
        }
    }
}
