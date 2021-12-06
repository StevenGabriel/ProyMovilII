package com.example.proymovilii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPssword;
    private Button btnAccept;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUser);
        etPssword = findViewById(R.id.etPassword);
        btnAccept = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    boolean success = false;
                    String user = etUser.getText().toString().trim();
                    String password = etPssword.getText().toString().trim();
                    if (user.equals("")){
                        etUser.setError("please insert user");
                        etUser.requestFocus();
                        return;
                    }
                    if (password.equals("")){
                        etPssword.setError("please insert password");
                        etPssword.requestFocus();
                        return;
                    }
                    ///Para realizar la consulta(select) usaremos el helper y tambien una instancia
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
                    SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                    //ahora ya podemos realizar el select, el resultado de la consulta sera un objjeto tipo cursor
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT user, password FROM users;",null);
                    if (cursor.moveToFirst()){
                        do {
                            String userCursor = cursor.getString(0);
                            String passwordCursor = cursor.getString(1);
                            if (userCursor.equals(user) && passwordCursor.equals(password)){
                                success = true;
                                Toast.makeText(LoginActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                break;
                            }
                        }while (cursor.moveToNext());
                    }

                }catch (Exception exception){
                    Toast.makeText(LoginActivity.this, "Error: " + exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}