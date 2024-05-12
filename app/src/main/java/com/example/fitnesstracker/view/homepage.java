package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstracker.DataBase.DataBaseHelper;
import com.example.fitnesstracker.R;
import com.example.fitnesstracker.controller.Controller;

public class homepage extends AppCompatActivity {
    private Controller controller;
    private TextView tvAge;
    private EditText etTaille,etPoids;
    private SeekBar sbAge;
    private RadioButton rbHomme;
    private RadioButton moderementActif,normal,tresActif,extraActif,leger,legerementActif;
    private Button btnconsult;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        init();
        controller=Controller.getInstance();
        //convertion des type
        float taille=Float.parseFloat(etTaille.getText().toString());
        float poids=Float.parseFloat(etPoids.getText().toString());
        int age=sbAge.getProgress();
        int gender=rbHomme.isChecked() ? 1:0;//homme==1 / femme==0
        String activity=getUserActivity();

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
        btnconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("USERNAME");
                String password = intent.getStringExtra("PASSWORD");
                controller.createUser(username,password,age,gender,taille,poids,activity);
                float calResult = controller.getCalResult();
                //isertion userinformation
               if (dbHelper.insertUserInfoData(username,age,gender,calResult,taille,poids,activity))
               {
                   Toast.makeText(homepage.this, " enregistrement des données avec Succées ", Toast.LENGTH_SHORT).show();}
               else {
                   Toast.makeText(homepage.this, " echec d'enregistrement", Toast.LENGTH_SHORT).show();
               }

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
        btnconsult=findViewById(R.id.btnConsultation);
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