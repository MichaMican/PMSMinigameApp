package de.sbr_cs.pmsminigameapp.CoinGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class CoinGameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private CoinGameManager coinGameManager;
    private Timer t;
    private Handler mHandler;

    public void triggerGameOver(int score){
        Message message = mHandler.obtainMessage(0, "GAMEOVER - Score: " + score);
        message.sendToTarget();
        t.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_game);

        coinGameManager = new CoinGameManager(this, (CoinGameView) findViewById(R.id.coinGameView));

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                AlertDialog alertDialog = new AlertDialog.Builder(CoinGameActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage((String) message.obj);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
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
