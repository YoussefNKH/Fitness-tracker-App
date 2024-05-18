package com.example.fitnesstracker.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnesstracker.DataBase.DataBaseHelper;
import com.example.fitnesstracker.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnConnexion,btnCreation;

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dbHelper=new DataBaseHelper(this);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if (dbHelper.checkUserNamePass(username, password)) {
                    Toast.makeText(MainActivity.this, "connexion avec succées :bienvenue "+username, Toast.LENGTH_SHORT).show();
                    // Create an Intent to start HomepageActivity
                   Intent intent = new Intent(MainActivity.this, homepage.class);

                    // Pass username and password as extras
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("PASSWORD", password);

                    // Start HomepageActivity
                    startActivity(intent);
                }
                else { Toast.makeText(MainActivity.this, " le username ou le mot de pass invalide ", Toast.LENGTH_SHORT).show();}
            }
        });
        btnCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, creation.class);
                startActivity(intent);

            }
        });
    }
    private void init(){
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextUsername=findViewById(R.id.editTextUsername);
        btnConnexion=findViewById(R.id.btnConnexion);
        btnCreation=findViewById(R.id.btnCreation);
        btnConnexion=findViewById(R.id.btnConnexion);

    }

}