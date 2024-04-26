package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnesstracker.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnConnexion,btnCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Create an Intent to start HomepageActivity
                Intent intent = new Intent(MainActivity.this, homepage.class);

                // Pass username and password as extras
                intent.putExtra("USERNAME", username);
                intent.putExtra("PASSWORD", password);

                // Start HomepageActivity
                startActivity(intent);
            }
        });
    }
    private void init(){
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextUsername=findViewById(R.id.editTextUsername);
        btnConnexion=findViewById(R.id.btnConnexion);
        btnCreation=findViewById(R.id.btnCreation);

    }

}