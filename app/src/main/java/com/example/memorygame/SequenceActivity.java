package com.example.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequenceActivity extends Activity {
    private TextView display;
    private final String[] colors = {"🔴", "🟢", "🔵", "🟣"};
    private final Random random = new Random();
    private final Handler handler = new Handler();
    private List<String> sequenceList = new ArrayList<>();
    private int sequenceLength;
    private int score;
    private int index = 0;
    private boolean showColor = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);
        display = findViewById(R.id.display);
        sequenceLength = getIntent().getIntExtra("sequence_length", 4);
        score = getIntent().getIntExtra("score", 0);

        showSequence();
    }

    private void showSequence() {
        if (index < sequenceLength * 2) {
            if (showColor) {
                String color = colors[random.nextInt(colors.length)];
                display.setText(color);
                sequenceList.add(color);
            } else {
                display.setText("");
            }
            showColor = !showColor;
            index++;
            handler.postDelayed(this::showSequence, 2000);
        } else {
            countdown();
        }
    }

    private void countdown() {
        new Thread(() -> {
            for (int i = 3; i > 0; i--) {
                final int finalI = i;
                runOnUiThread(() -> display.setText(String.valueOf(finalI)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> {
                Intent intent = new Intent(SequenceActivity.this, PlayActivity.class);
                intent.putStringArrayListExtra("sequence", new ArrayList<>(sequenceList));
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}
