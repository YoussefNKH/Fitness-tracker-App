package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.controller.Controller;

public  class Result extends AppCompatActivity {
    private TextView tvIMCreponse, tvResponse;
    private Button btnRetour, btnnbrpas;
    private Controller controller;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        controller = Controller.getInstance();

        // Retrieve data from intent
        Intent intent = getIntent();
        float calResult = intent.getFloatExtra("CAL_RESULT", 0);
        float imcResult = intent.getFloatExtra("IMC_RESULT", 0);
        String imcType = intent.getStringExtra("IMC_TYPE");

        // Display the retrieved data
        tvResponse.setText(String.valueOf(calResult));
        tvIMCreponse.setText(String.valueOf(imcResult) + " \n Vous êtes : " + imcType);

        btnnbrpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Steps.class);
                startActivity(intent);
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, homepage.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tvIMCreponse = findViewById(R.id.tvIMCreponse);
        tvResponse = findViewById(R.id.tvResponse);
        btnnbrpas = findViewById(R.id.btnnbrpas);
        btnRetour = findViewById(R.id.btnRetour);
    }
}