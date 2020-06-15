package de.sbr_cs.pmsminigameapp.CoinGame;

import androidx.appcompat.app.AppCompatActivity;
import de.sbr_cs.pmsminigameapp.R;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class CoinGameActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private CoinGameView coinGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_game);

        coinGameView = (CoinGameView) findViewById(R.id.coinGameView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];

            int dx = -(int) Math.ceil(x);
            int dy = (int) Math.ceil(y);

            coinGameView.move(dx, dy);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
