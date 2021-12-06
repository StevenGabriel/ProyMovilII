package com.example.proymovilii.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.proymovilii.DataBaseHelper;
import com.example.proymovilii.Models.User;

public class UserController {
    DataBaseHelper dataBaseHelper;

    public UserController(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public long insertUser(User user){
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName() );
        contentValues.put("phone", user.getPhone() );
        contentValues.put("email", user.getEmail() );
        contentValues.put("user", user.getUser() );
        contentValues.put("password", user.getPassword() );

        return sqLiteDatabase.insert(dataBaseHelper.getTableUsers(),null,contentValues);
    }

    public int updateUser(User user){

        return 0;
    }
}
