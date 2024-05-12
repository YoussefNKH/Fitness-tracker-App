package com.example.fitnesstracker.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnesstracker.R;

public class creation extends AppCompatActivity {
    private EditText editTextUsername,editTextEmail,editTextPassword,editTextConfirmPassword;
    private TextView tvMdpsIncorrespondents;
    private Button btnCreation;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_creation);
            init();


    }
    private  void init(){
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        btnCreation=findViewById(R.id.btnCreation);


    }
}
