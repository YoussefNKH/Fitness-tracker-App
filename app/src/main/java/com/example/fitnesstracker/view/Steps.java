package com.example.fitnesstracker.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.fitnesstracker.R;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Steps extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView stepCountTextView;
    private TextView caloriesBurnedTextView;
    private TextView distanceTextView;
    private int stepCount = 0;
    private float distanceWalked = 0; // in kilometers
    private float caloriesBurned = 0;
    private Button btnGit;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        btnGit=findViewById(R.id.btnGit);
        btnGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/YoussefNKH/Fitness-tracker-App"));
                startActivity(intent);
            }
        });
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepCountTextView = findViewById(R.id.stepCountTextView);
        caloriesBurnedTextView = findViewById(R.id.caloriesBurnedTextView);
        distanceTextView = findViewById(R.id.distanceTextView);

        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            stepCountTextView.setText("Step counter sensor not available");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            stepCount = (int) event.values[0];
            stepCountTextView.setText("Nombre de pas : " + stepCount);
            float stepLength = 0.75f;
            distanceWalked = stepCount * stepLength / 1000; // Convert to kilometers
            distanceTextView.setText("Distance parcourue : " + distanceWalked + " km");
            float caloriesPerKm = 0.05f;
            caloriesBurned = distanceWalked * caloriesPerKm;
            caloriesBurnedTextView.setText("Calories brûlées : " + caloriesBurned);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register listener when the activity is resumed
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister listener when the activity is paused to conserve battery
        sensorManager.unregisterListener(this);
    }
}
