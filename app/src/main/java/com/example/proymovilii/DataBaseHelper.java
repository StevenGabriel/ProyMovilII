package com.example.proymovilii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE = "Proyecto.db";
    private static final int VERSION = 1;
    private static final String TABLE_USERS = "users";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATA_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "phone INTEGER NOT NULL, " +
                "email TEXT NOT NULL, " +
                "user TEXT NOT NULL, " +
                "password TEXT NOT NULL)",TABLE_USERS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static String getDataBase() {
        return DATA_BASE;
    }

    public static int getVERSION() {
        return VERSION;
    }

    public static String getTableUsers() {
        return TABLE_USERS;
    }
}
