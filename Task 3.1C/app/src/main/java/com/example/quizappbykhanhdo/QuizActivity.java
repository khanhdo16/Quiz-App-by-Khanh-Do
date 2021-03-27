package com.example.quizappbykhanhdo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    String name;
    TextView quizTextView;
    TextView questionTextView;
    TextView questionDetailTextView;
    TextView progressTextView;
    ProgressBar progressBar;
    Button submitButton;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button[] buttons;
    String[] questions;
    String[] answers;
    int selectedAnswer = -1;
    int progress = 0;
    boolean checked = false;
    int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        quizTextView = findViewById(R.id.quizTextView);
        questionTextView = findViewById(R.id.questionTextView);
        questionDetailTextView = findViewById(R.id.questionDetailTextView);
        progressTextView = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);

        submitButton = findViewById(R.id.submitButton);
        answerButton1 = findViewById(R.id.button1);
        answerButton2 = findViewById(R.id.button2);
        answerButton3 = findViewById(R.id.answerButton3);
        buttons  = new Button[]{answerButton1,answerButton2,answerButton3};

        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        quizTextView.setText("Welcome, " + name + "!");

        loadQuestions();
    }

    public void loadQuestions() {
        if (progress > 0 ) quizTextView .setVisibility(quizTextView.GONE);
        progressTextView.setText(Integer.toString(progress + 1) + "/5" );
        progressBar.setProgress((progress + 1) * 20);

        questionTextView.setText(questions[progress]);
        for(int i = 0; i < 3; i++) {
            answerButton1.setText(answers[progress * 3]);
            answerButton2.setText(answers[(progress * 3) + 1]);
            answerButton3.setText(answers[(progress * 3) + 2]);
        }
    }

    public void selectAnswer(View view) {
        if (submitButton.getText().toString().equals("Submit")) {
            checked = false;
            for (int i = 0; i < buttons.length; i++) {
                if (view.getId() == buttons[i].getId()) {
                    selectedAnswer = i;
                    buttons[i].setBackgroundColor(getResources().getColor(R.color.vibrant_blue));
                } else buttons[i].setBackgroundColor(getResources().getColor(R.color.blue));
            }
        }
    }

    public void validation(View view) {
        String[] correct = getResources().getStringArray(R.array.correct);

        if (selectedAnswer <= -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (submitButton.getText().toString().equals("Submit")) {
                for (String answer : correct) {
                    if (buttons[selectedAnswer].getText().toString().equals(answer)) {
                        buttons[selectedAnswer].setBackgroundColor(getResources().getColor(R.color.correct));
                        checked = true;
                        score++;
                    }
                    if (checked == false) {
                        buttons[selectedAnswer].setBackgroundColor(getResources().getColor(R.color.wrong));
                        for (Button button : buttons) {
                            if (button.getText().toString().equals(answer)) {
                                button.setBackgroundColor(getResources().getColor(R.color.correct));
                            }
                        }

                    }
                }
                if (checked == true) {
                    Toast.makeText(this, "You got one point!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No point, wrong answer!", Toast.LENGTH_SHORT).show();
                }
                submitButton.setText("Next");
            } else {
                if (progress == 4) {
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("name", name);
                    startActivity(intent);
                } else {
                    if (progress < 4) progress++;
                    for (Button button : buttons) {
                        button.setBackgroundColor(getResources().getColor(R.color.blue));
                    }
                    submitButton.setText("Submit");
                    selectedAnswer = -1;
                    loadQuestions();
                }
            }
        }
    }
}