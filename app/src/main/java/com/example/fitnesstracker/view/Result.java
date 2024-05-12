package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.controller.Controller;

public class Result extends AppCompatActivity {
    private TextView tvIMCreponse,tvResponse;
    private Button btnRetour,btnnbrpas;
    private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        controller=Controller.getInstance();
        btnnbrpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, Steps.class);
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, homepage.class);
            }
        });
        float result=controller.getCalResult();
        float resultIMC=controller.getIMCResult();
        String TypeIMC=controller.getIMCType();
        tvResponse.setText(String.valueOf(result));
        tvIMCreponse.setText(String.valueOf(resultIMC)+"Vous éte :"+TypeIMC);

    }
    private void init(){
        tvIMCreponse=findViewById(R.id.tvIMCreponse);
        tvResponse=findViewById(R.id.tvResponse);
        btnnbrpas=findViewById(R.id.btnnbrpas);
        btnRetour=findViewById(R.id.btnRetour);
    }
}
