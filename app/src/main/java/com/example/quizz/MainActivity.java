package com.example.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseAccess databaseAccess;
    List<Question> questionList;

    TextView number_question, question, answer_1, answer_2, answer_3, answer_4;
    int index = 0;
    String correctAnswer;
    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAccess = DatabaseAccess.getInstance(this);
        questionList = databaseAccess.questionList();

        number_question = findViewById(R.id.number_question);
        question = findViewById(R.id.question);
        answer_1 = findViewById(R.id.answer_1);
        answer_2 = findViewById(R.id.answer_2);
        answer_3 = findViewById(R.id.answer_3);
        answer_4 = findViewById(R.id.answer_4);

        bind();
    }

    private void bind() {
        if ((index + 1) > questionList.size()) {
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("point", point);
            startActivity(intent);
            return;
        }
        String numberQuestion = (index + 1) + "/" + questionList.size();
        Question question_2 = questionList.get(index);
        question.setText(question_2.getQuestion());
        List<Answer> answersList = question_2.getAnswers();
        String number_question_2 = numberQuestion;
        number_question.setText(number_question_2);
        answer_1.setText(answersList.get(0).answer);
        answer_2.setText(answersList.get(1).answer);
        answer_3.setText(answersList.get(2).answer);
        answer_4.setText(answersList.get(3).answer);

        for (int i = 0; i < answersList.size(); i++) {
            if (answersList.get(i).isCorrect) {
                correctAnswer = answersList.get(i).answer;
                break;
            }
        }
    }

    public void next(View view) {
        TextView textView = (TextView) view;
        if (textView.getText().toString() == correctAnswer) {
            point++;
        }
        index++;
        bind();
    }
}
