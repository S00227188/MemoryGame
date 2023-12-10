package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

public class OverActivity extends AppCompatActivity {
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
        score = getIntent().getIntExtra("score", 0);

        TextView scoreView = findViewById(R.id.scoreView);
        scoreView.setText("Your score was: " + score);

        Button saveScoreButton = findViewById(R.id.saveScoreButton);
        saveScoreButton.setOnClickListener(v -> promptForName());

        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(OverActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button viewHighScoresButton = findViewById(R.id.viewHighScoresButton);
        viewHighScoresButton.setOnClickListener(v -> {
            Intent intent = new Intent(OverActivity.this, ScoresActivity.class);
            startActivity(intent);
        });
    }

    private void promptForName() {
        final EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Enter Name")
                .setMessage("Please enter your name:")
                .setView(input)
                .setPositiveButton("Save", (dialog, whichButton) -> {
                    String name = input.getText().toString();
                    saveScore(name);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveScore(String name) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.addHighscore(name, score);
    }
}
