package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.controller.Controller;

public class homepage extends AppCompatActivity {
    private Controller controller;
    private TextView tvAge;
    private EditText etTaille,etPoids;
    private SeekBar sbAge;
    private RadioButton rbHomme;
    private RadioButton moderementActif,normal,tresActif,extraActif,leger,legerementActif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        init();
        controller=Controller.getInstance();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("information","on progressChange"+progress);
                tvAge.setText("Votre age : "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void init(){
        etTaille=findViewById(R.id.etTaille);
        etPoids=findViewById(R.id.etPoids);
        sbAge=findViewById(R.id.sbAge);
        rbHomme=findViewById(R.id.rbHomme);
        moderementActif=findViewById(R.id.moderementActif);
        normal=findViewById(R.id.normal);
        tresActif=findViewById(R.id.tresActif);
        extraActif=findViewById(R.id.extraActif);
        leger=findViewById(R.id.leger);
        legerementActif=findViewById(R.id.legerementActif);
        tvAge=findViewById(R.id.tvAge);
    }
    private void calculateCalories(){
        float taille=Float.parseFloat(etTaille.getText().toString());
        float poids=Float.parseFloat(etPoids.getText().toString());
        int age=sbAge.getProgress();
        int gender=rbHomme.isChecked() ? 1:0;//homme==1 / femme==0
        String activity=getUserActivity();
        Intent intent = getIntent();
       String username = intent.getStringExtra("USERNAME");
        String password = intent.getStringExtra("PASSWORD");


        controller.createUser(username,password,age,gender,taille,poids,activity);

    }
    private String getUserActivity(){
        if (moderementActif.isChecked()) {
            return "modérément actif";
        } else if (normal.isChecked()) {
            return "normal";
        } else if (tresActif.isChecked()) {
            return "très actif";
        } else if (extraActif.isChecked()) {
            return "extra actif";
        } else if (legerementActif.isChecked()) {
            return "légèrement actif";
        } else if (leger.isChecked()) {
            return "sédentaire";
        }
        return "";
    }
    }


