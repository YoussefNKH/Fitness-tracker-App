package com.example.fitnesstracker.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fitnesstracker.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the 'users' table
        String createUserTableQuery = "CREATE TABLE users (" +
                "user_name TEXT PRIMARY KEY," +
                "email TEXT," +
                "password TEXT)";
        db.execSQL(createUserTableQuery);

        // Create the 'user_info' table
        String createUserInfoTableQuery = "CREATE TABLE user_info (" +
                "user_id INTEGER PRIMARY KEY," +
                "user_name TEXT," +
                "age INTEGER," +
                "gender INTEGER," +
                "calories REAL," +
                "longueur REAL," +
                "poids REAL," +
                "activity TEXT," +
                "FOREIGN KEY(user_name) REFERENCES users(user_name))";
        db.execSQL(createUserInfoTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS user_info");
        // Create tables again
        onCreate(db);
    }

    public boolean insertUserData(String userName, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", userName);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);

        return result != -1;
    }

    public boolean insertUserInfoData(String userName, int age, int gender, float calories, float longueur, float poids, String activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", userName);
        contentValues.put("age", age);
        contentValues.put("gender", gender);
        contentValues.put("calories", calories);
        contentValues.put("longueur", longueur);
        contentValues.put("poids", poids);
        contentValues.put("activity", activity);
        long result = db.insert("user_info", null, contentValues);

        return result != -1;
    }

    public boolean checkUserName(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_name = ?", new String[]{userName});
        int count = cursor.getCount();


        return count > 0;
    }

    public boolean checkUserNamePass(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_name = ? AND password = ?", new String[]{userName, password});
        int count = cursor.getCount();


        return count > 0;
    }
}
