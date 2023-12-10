package com.example.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "memoryGameDatabase";
    private static final String TABLE_HIGHSCORE = "highscore";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_HIGHSCORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }

    void addHighscore(String name, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_SCORE, score);
        db.insert(TABLE_HIGHSCORE, null, values);
        db.close();
    }
    public List<String> getTopHighscores() {
        List<String> highscoreList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_HIGHSCORE + " ORDER BY " + KEY_SCORE + " DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(KEY_NAME);
                int scoreIndex = cursor.getColumnIndex(KEY_SCORE);
                if (nameIndex != -1 && scoreIndex != -1) {
                    String scoreEntry = cursor.getString(nameIndex) + ": " + cursor.getInt(scoreIndex);
                    highscoreList.add(scoreEntry);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return highscoreList;
    }
}
