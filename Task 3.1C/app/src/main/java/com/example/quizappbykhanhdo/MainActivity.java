package com.example.quizappbykhanhdo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    TextView previousResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditText);
        previousResultTextView = findViewById(R.id.previousResultTextView);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        String name = intent.getStringExtra("name");

        nameEditText.setText(name);
        previousResultTextView.setText("PREVIOUS SCORE: " + Integer.toString(score) + "/5"); 
    }

    public void startQuiz(View view) {
        if (isEmpty(nameEditText.getText().toString())) {
            Toast.makeText(this, "Please enter a name!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("name", nameEditText.getText().toString());
            startActivity(intent);
        }
    }
}