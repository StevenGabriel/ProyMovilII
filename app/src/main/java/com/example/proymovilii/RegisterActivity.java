package com.example.proymovilii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proymovilii.Controllers.UserController;
import com.example.proymovilii.Models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etPhone, etEmail, etUser, etPassword;
    private Button btnRegister;
    private User user;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etNameRegister);
        etPhone = findViewById(R.id.etPhoneRegister);
        etEmail = findViewById(R.id.etEmailRegister);
        etUser = findViewById(R.id.etUserRegister);
        etPassword = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);

        userController = new UserController(RegisterActivity.this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nameString = etName.getText().toString().trim();
                    String phoneString = etPhone.getText().toString().trim();
                    String emailString = etEmail.getText().toString().trim();
                    String userString = etUser.getText().toString().trim();
                    String passwordString = etPassword.getText().toString().trim();

                    if (nameString.equals("")){
                        etName.setError("Please insert name");
                        etName.requestFocus();
                    }
                    if (phoneString.equals("")){
                        etPhone.setError("Please insert phone");
                        etPhone.requestFocus();
                    }
                    if (emailString.equals("")){
                        etEmail.setError("Please insert email");
                        etEmail.requestFocus();
                    }
                    if (userString.equals("")){
                        etUser.setError("Please insert user");
                        etUser.requestFocus();
                    }
                    if (passwordString.equals("")){
                        etPassword.setError("Please insert password");
                        etPassword.requestFocus();
                    }

                    int phoneInt = Integer.parseInt(phoneString);

                    user = new User(phoneInt, nameString, emailString, userString, passwordString);
                    long result = userController.insertUser(user);
                    if (result == -1){
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Insert success", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }catch (Exception ex){
                    Toast.makeText(RegisterActivity.this, "Error" + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}