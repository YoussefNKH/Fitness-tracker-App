package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnesstracker.R;

public class Result extends AppCompatActivity {
    private TextView tvResponse;
    private TextView tvIMCreponse;
    private Button btnRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();

    }
    private void init(){
        tvResponse=findViewById(R.id.tvResponse);
        tvIMCreponse=findViewById(R.id.tvIMCreponse);
        btnRetour=findViewById(R.id.btnRetour);
    }
}
