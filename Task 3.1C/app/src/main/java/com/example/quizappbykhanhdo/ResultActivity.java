package com.example.quizappbykhanhdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView resultWelcomeTextView;
    TextView resultTextView;
    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int score = intent.getIntExtra("score", 0);

        resultTextView = findViewById(R.id.resultTextView);
        resultWelcomeTextView = findViewById(R.id.resultWelcomeTextView);

        resultWelcomeTextView.setText("Welcome, " + name);
        resultTextView.setText(Integer.toString(score) + "/5");

        intent1 = new Intent(this, MainActivity.class);
        intent1.putExtra("name", name);
        intent1.putExtra("score", score);
    }

    public void finishQuiz(View view) {
        finishAffinity();
    }

    public void newQuiz(View view) {
        startActivity(intent1);
    }
}