package de.sbr_cs.pmsminigameapp.LabyrinthGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.sbr_cs.pmsminigameapp.CoinGame.CoinGameActivity;
import de.sbr_cs.pmsminigameapp.FullScreenView;
import de.sbr_cs.pmsminigameapp.R;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

public class LabyrinthGameActivity extends AppCompatActivity {

    private LabyrinthGameManager labyrinthGameManager;
    private FloatingActionButton speakButton;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private Handler mHandler;
    private FullScreenView fullScreenView;

    private Locale getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

    public void triggerGameOver(String commandHistory){
        fullScreenView.show();
        speakButton.hide();
        Message message = mHandler.obtainMessage(1, "Oh no! You bumped into a wall :(\nYour command history:\n" + commandHistory);
        message.sendToTarget();
    }

    public void triggerSuccessGameOver() {
        fullScreenView.show();
        speakButton.hide();
        Message message = mHandler.obtainMessage(2, "You did it! You successfully made it through the maze");
        message.sendToTarget();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth_game);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getCurrentLocale(getApplicationContext()));

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                AlertDialog alertDialog = new AlertDialog.Builder(LabyrinthGameActivity.this).create();
                alertDialog.setTitle("GAME OVER");
                alertDialog.setMessage((String) message.obj);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                LabyrinthGameActivity.this.finish();
                            }
                        });
                alertDialog.show();
            }
        };

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.stopListening, Toast.LENGTH_SHORT);
                toast.show();
                String[] words = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        .get(0).split(" ");
                labyrinthGameManager.applyCommands(words);
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        speakButton = findViewById(R.id.speakButton);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                speechRecognizer.startListening(speechRecognizerIntent);
                Toast toast = Toast.makeText(getApplicationContext(), R.string.listening, Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        speakButton.hide();

        fullScreenView = findViewById(R.id.labyrinthGameView);

        labyrinthGameManager = new LabyrinthGameManager(this, fullScreenView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                speakButton.show();
                fullScreenView.hide();
            }
        }, 10000);

    }

    private boolean checkPermission() {

        if(ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.RECORD_AUDIO) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LabyrinthGameActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 2);

        }

        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED;

    }


}
