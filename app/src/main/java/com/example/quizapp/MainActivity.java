package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButon;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArrayList;
    private String questionString;
    private String answer;
    int currentPosition=0;
    int numberOfCorrectAnswer=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountLabel = findViewById(R.id.noQuestion);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdt = findViewById(R.id.answer);
        submitButon = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);

        questionModelArrayList = new ArrayList<>();

        setUpQuestion();
        setData();

        submitButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();
            }
        });
    }

    public void checkAnswer(){

        String answerString = answerEdt.getText().toString().trim();
        String tag;
        String msg;
        if(answerString.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getAnswer()))
        {
            numberOfCorrectAnswer++;

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good Job")
                    .setContentText("Right Answer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            currentPosition++;             //to go on next question after submitting the question
                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();

                        }
                    })
                    .show();
        }
        else{

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The right answer is : "+questionModelArrayList.get(currentPosition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismiss();              //to go on next question after submitting the question

                            currentPosition++;

                            setData();
                            answerEdt.setText("");
                        }
                    })
                    .show();
        }

        int x = ((currentPosition+1) * 100) / questionModelArrayList.size();
        progressBar.setProgress(x);
    }
    public void setUpQuestion() {

        questionModelArrayList.add(new QuestionModel( questionString= "What is 1 + 2 ?", answer= "3"));
        questionModelArrayList.add(new QuestionModel( questionString= "What is 2 + 3 * 6 ?", answer= "20"));
        questionModelArrayList.add(new QuestionModel( questionString= "What is  3 - 3 * 6 + 2 ?", answer= "-13"));
        questionModelArrayList.add(new QuestionModel( questionString= "What is 6 + 8 * 4 - 2 ?", answer= "36"));
        questionModelArrayList.add(new QuestionModel( questionString= "What is 6 + 8 * (4 - 2) ?", answer= "22"));

    }

    public void setData() {

        questionLabel.setText(questionModelArrayList.get(currentPosition).getQuestionString());
        questionCountLabel.setText("Question No : " + (currentPosition+1));
        scoreLabel.setText("Score : " +numberOfCorrectAnswer+ "/" +questionModelArrayList.size());
    }
}
