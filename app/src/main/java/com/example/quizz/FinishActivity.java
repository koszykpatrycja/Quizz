package com.example.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    TextView score, numberScore;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        score = findViewById(R.id.score);
        numberScore = findViewById(R.id.number_score);
        setPoint();
    }

    private void setPoint() {
        Intent intent = getIntent();
        int message = intent.getIntExtra("point", 0);
        numberScore.setText(String.valueOf(message));
    }
}
