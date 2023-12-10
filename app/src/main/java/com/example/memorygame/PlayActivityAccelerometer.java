/*
package com.example.memorygame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PlayActivityAccelerometer extends AppCompatActivity implements SensorEventListener {
    private ArrayList<String> sequence;
    private int currentStep = 0;
    private int score;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isFlat = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button btnRed = findViewById(R.id.btnRed);
        Button btnGreen = findViewById(R.id.btnGreen);
        Button btnBlue = findViewById(R.id.btnBlue);
        Button btnPurple = findViewById(R.id.btnPurple);

        sequence = getIntent().getStringArrayListExtra("sequence");
        score = getIntent().getIntExtra("score", 0);

        btnRed.setOnClickListener(this::onColorButtonClicked);
        btnGreen.setOnClickListener(this::onColorButtonClicked);
        btnBlue.setOnClickListener(this::onColorButtonClicked);
        btnPurple.setOnClickListener(this::onColorButtonClicked);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onColorButtonClicked(View view) {
        String selectedColor = "";
        if (view.getId() == R.id.btnRed) {
            selectedColor = "🔴";
        } else if (view.getId() == R.id.btnGreen) {
            selectedColor = "🟢";
        } else if (view.getId() == R.id.btnBlue) {
            selectedColor = "🔵";
        } else if (view.getId() == R.id.btnPurple) {
            selectedColor = "🟣";
        }

        checkSelection(selectedColor);
    }

    private void checkSelection(String selectedColor) {
        if(selectedColor.equals(sequence.get(currentStep))) {
            currentStep++;
            score++;
            if(currentStep == sequence.size()) {
                navigateToNext();
            }
        } else {
            navigateToGameOver();
        }
    }

    private void navigateToNext() {
        Intent intent = new Intent(PlayActivity.this, SequenceActivity.class);
        intent.putExtra("sequence_length", sequence.size() + 2);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    private void navigateToGameOver() {
        Intent intent = new Intent(PlayActivity.this, OverActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];

        if (Math.abs(x) < 2 && Math.abs(y) < 2) {
            if (!isFlat) {
                isFlat = true;
            }
            return;
        }

        if (isFlat) {
            isFlat = false;
            if (y < -2) { // North - Red
                findViewById(R.id.btnRed).performClick();
            } else if (y > 2) { // South - Green
                findViewById(R.id.btnGreen).performClick();
            } else if (x < -2) { // West - Purple
                findViewById(R.id.btnPurple).performClick();
            } else if (x > 2) { // East - Blue
                findViewById(R.id.btnBlue).performClick();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
*/
