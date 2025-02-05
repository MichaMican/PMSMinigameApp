package de.sbr_cs.pmsminigameapp.CoinGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.sbr_cs.pmsminigameapp.FullScreenView;
import de.sbr_cs.pmsminigameapp.R;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Coin game activity class
 */
public class CoinGameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private CoinGameManager coinGameManager;
    private Timer t;
    private Handler mHandler;

    /**
     * Displays game over message with score
     * @param score Score that the player achieved
     */
    public void triggerGameOver(int score){
        Message message = mHandler.obtainMessage(0, "GAMEOVER - Score: " + score);
        message.sendToTarget();
        t.cancel();
    }

    /**
     * Initializes the activity
     * @param savedInstanceState Saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_game);

        coinGameManager = new CoinGameManager(this, (FullScreenView) findViewById(R.id.coinGameView));

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                AlertDialog alertDialog = new AlertDialog.Builder(CoinGameActivity.this).create();
                alertDialog.setTitle(R.string.gameOver);
                alertDialog.setMessage((String) message.obj);
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                CoinGameActivity.this.finish();
                            }
                        });
                alertDialog.show();
            }
        };

        t = new Timer(false);
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                coinGameManager.step();
            }
        }, 0, 10);
    }

    /**
     * Notifies the coinGameManager about a sensor change
     * @param event Sensor event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];

            coinGameManager.sensorUpdate(x, y);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
