package com.example.quizmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    ProgressBar progressBar;
    TextView textView;
    TextView scoreText;
    int index;
    int question;
    int score;
    Toast toastMsg;

    // TODO: Uncomment to create question bank
    private Quiz[] mQuestionBank = new Quiz[] {
            new Quiz(R.string.question_1, true),
            new Quiz(R.string.question_2, true),
            new Quiz(R.string.question_3, true),
            new Quiz(R.string.question_4, true),
            new Quiz(R.string.question_5, true),
            new Quiz(R.string.question_6, false),
            new Quiz(R.string.question_7, true),
            new Quiz(R.string.question_8, false),
            new Quiz(R.string.question_9, true),
            new Quiz(R.string.question_10, true),
            new Quiz(R.string.question_11, false),
            new Quiz(R.string.question_12, false),
            new Quiz(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR = (int) Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.trueBtn);
        mFalseButton = (Button)findViewById(R.id.falseBtn);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        scoreText = (TextView) findViewById(R.id.score);
        textView = (TextView) findViewById(R.id.quiz_qtts);


        // Restores the 'state' of the app upon screen rotation:
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("ScoreKey");
            index = savedInstanceState.getInt("IndexKey");
            scoreText.setText("Score " + score + "/" + mQuestionBank.length);
        } else {
            score = 0;
            index = 0;
        }
         question = mQuestionBank[index].getQuestionID();
         textView.setText(question);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("QuizMad: ", "Button Pressed!");
                CheckAnswer(true);
                UpdateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Quizz", "False button pressed");
                CheckAnswer(false);
                UpdateQuestion();
            }
        });
    }

    public void UpdateQuestion()
    {
        index = (index + 1) % mQuestionBank.length;
        if (index == 0)
        {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(MainActivity.this);
            alertdialog.setTitle("Game Over");
            alertdialog.setMessage("You scored " + score + " points!");
            alertdialog.setCancelable(false);
            alertdialog.setPositiveButton("close quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            alertdialog.show();
        }

        question = mQuestionBank[index].getQuestionID();
        textView.setText(question);
        progressBar.incrementProgressBy(PROGRESS_BAR);
        scoreText.setText("Score :" + score + "/" + mQuestionBank.length);
    }

    protected void CheckAnswer(boolean userAnswer)
    {
        boolean correctanswer = mQuestionBank[index].isAnswer();

        if (toastMsg != null)
        {
            toastMsg.cancel();
        }
        if (userAnswer == correctanswer)
        {
            toastMsg = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            score += 1;
        }
        else
        {
            toastMsg = Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT);
        }

        toastMsg.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", score);
        outState.putInt("IndexKey", index);
    }
}