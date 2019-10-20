package com.example.quizz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess databaseAccess;

    private DatabaseAccess(Context context) {
        this.sqLiteOpenHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (databaseAccess == null) {
            databaseAccess = new DatabaseAccess(context);
        }
        return databaseAccess;
    }

    public void open() {
        this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

    public List<Question> questionList() {
        List<Question> questionList = new ArrayList<>();
        open();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id, text FROM questions", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String questionText = cursor.getString(1);
            Question question = new Question(id, questionText, getAnswers(id));
            questionList.add(question);
        }
        cursor.close();
        return questionList;
    }

    private List<Answer> getAnswers(int idQuestion) {
        List<Answer> answers = new ArrayList<>();
        open();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT text, is_correct FROM answers WHERE id_question = " + idQuestion, null);
        while (cursor.moveToNext()) {
            String answerText = cursor.getString(0);
            boolean isCorrect = cursor.getInt(1) == 0;
            Answer answer = new Answer(answerText, isCorrect);
            answers.add(answer);
        }
        cursor.close();
        return answers;
    }

}
