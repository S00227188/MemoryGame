package com.example.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private ArrayList<String> sequence;
    private int currentStep = 0;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

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

        if(selectedColor.equals(sequence.get(currentStep))) {
            currentStep++;
            score++;
            if(currentStep == sequence.size()) {
                Intent intent = new Intent(PlayActivity.this, SequenceActivity.class);
                intent.putExtra("sequence_length", sequence.size() + 2);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(PlayActivity.this, OverActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }
}
