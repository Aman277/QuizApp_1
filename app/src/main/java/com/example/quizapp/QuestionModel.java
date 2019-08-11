package com.example.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuestionModel {

    public QuestionModel(String questionString, String answer)
    {
        QuestionString = questionString;
        Answer = answer;
    }

    public String getQuestionString() { return QuestionString; }

    public  void setQuestionString(String questionString)  { QuestionString = questionString; }

    public String getAnswer()  { return Answer; }

    public  void setAnswer(String answer)  { Answer = answer; }

    private  String QuestionString;
    private String Answer;



}
