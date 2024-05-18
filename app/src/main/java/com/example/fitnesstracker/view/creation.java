package com.example.fitnesstracker.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnesstracker.DataBase.DataBaseHelper;
import com.example.fitnesstracker.R;

public class creation extends AppCompatActivity {
    private EditText editTextUsername,editTextEmail,editTextPassword,editTextConfirmPassword;

    private Button btnCreation;
    private DataBaseHelper dbHelper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_creation);
            init();
        dbHelper=new DataBaseHelper(this);

               btnCreation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = editTextUsername.getText().toString().trim();
                        String email = editTextEmail.getText().toString().trim();
                        String pass = editTextPassword.getText().toString().trim();
                        String repass = editTextConfirmPassword.getText().toString().trim();

                        if(user.isEmpty() || email.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                            Toast.makeText(creation.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        } else {
                            if(pass.equals(repass)) {
                                Boolean checkUser = dbHelper.checkUserName(user);
                                if(!checkUser) {
                                    boolean insert = dbHelper.insertUserData(user, email, pass);
                                    if(insert) {
                                        Toast.makeText(creation.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), homepage.class);
                                        intent.putExtra("USERNAME", user);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(creation.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(creation.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(creation.this, "Password not matching", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


    }
    private  void init(){
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        btnCreation=findViewById(R.id.btnCreation);


    }
}
