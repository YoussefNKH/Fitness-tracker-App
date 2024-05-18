package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstracker.DataBase.DataBaseHelper;
import com.example.fitnesstracker.R;
import com.example.fitnesstracker.controller.Controller;

public class homepage extends AppCompatActivity {
    private Controller controller;
    private TextView tvAge;
    private EditText etTaille, etPoids;
    private SeekBar sbAge;
    private RadioButton rbHomme;
    private RadioButton moderementActif, normal, tresActif, extraActif, leger, legerementActif;
    private Button btnconsult;
    private DataBaseHelper dbHelper;
    private RadioGroup rdg;

    private void init() {
        etTaille = findViewById(R.id.etTaille);
        etPoids = findViewById(R.id.etPoids);
        sbAge = findViewById(R.id.sbAge);
        rbHomme = findViewById(R.id.rbHomme);
        moderementActif = findViewById(R.id.moderementActif);
        normal = findViewById(R.id.normal);
        tresActif = findViewById(R.id.tresActif);
        extraActif = findViewById(R.id.extraActif);
        leger = findViewById(R.id.leger);
        legerementActif = findViewById(R.id.legerementActif);
        tvAge = findViewById(R.id.tvAge);
        btnconsult = findViewById(R.id.btnConsultation);
        rdg = findViewById(R.id.rdg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Log.d("homepage", "setContentView called");
        init();
        dbHelper = new DataBaseHelper(this);
        Log.d("homepage", "init called");

        controller = Controller.getInstance();

        // Initialize age display
        tvAge.setText("Votre age : " + sbAge.getProgress());

        // Set SeekBar change listener
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("homepage", "onProgressChanged: " + progress);
                tvAge.setText("Votre age : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tailleText = etTaille.getText().toString();
                String poidsText = etPoids.getText().toString();

                if (!tailleText.isEmpty() && !poidsText.isEmpty()) {
                    float taille = Float.parseFloat(tailleText);
                    float poids = Float.parseFloat(poidsText);
                    int age = sbAge.getProgress();
                    int gender = rbHomme.isChecked() ? 1 : 0; // homme==1 / femme==0
                    String activity = getUserActivity();

                    Intent intent = getIntent();
                    String username = intent.getStringExtra("USERNAME");
                    String password = intent.getStringExtra("PASSWORD");
                    controller.createUser(username, password, age, gender, taille, poids, activity);
                    float calResult = controller.getCalResult();
                    float imcResult = controller.getIMCResult();
                    String imcType = controller.getIMCType();

                    // Insertion user information
                    if (dbHelper.insertUserInfoData(username, age, gender, calResult, taille, poids, activity)) {
                        Toast.makeText(homepage.this, "Enregistrement des données avec succès", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(homepage.this, "Échec d'enregistrement", Toast.LENGTH_SHORT).show();
                    }

                    // Passing data to Result activity
                    Intent resultIntent = new Intent(homepage.this, Result.class);
                    resultIntent.putExtra("CAL_RESULT", calResult);
                    resultIntent.putExtra("IMC_RESULT", imcResult);
                    resultIntent.putExtra("IMC_TYPE", imcType);
                    startActivity(resultIntent);
                } else {
                    Toast.makeText(homepage.this, "Please enter valid values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getUserActivity() {
        int checkedRadioButtonId = rdg.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.moderementActif) {
            return "modérément actif";
        } else if (checkedRadioButtonId == R.id.normal) {
            return "normal";
        } else if (checkedRadioButtonId == R.id.tresActif) {
            return "très actif";
        } else if (checkedRadioButtonId == R.id.extraActif) {
            return "extra actif";
        } else if (checkedRadioButtonId == R.id.legerementActif) {
            return "légèrement actif";
        } else if (checkedRadioButtonId == R.id.leger) {
            return "sédentaire";
        }
        return "";
    }
}