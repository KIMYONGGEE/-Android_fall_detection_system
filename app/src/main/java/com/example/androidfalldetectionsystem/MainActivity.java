package com.example.androidfalldetectionsystem;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerSensor;
    private Sensor linearAccelerSensor;
    private float gAccX, gAccY, gAccZ, accX, accY, accZ, lAccX, lAccY, lAccZ ;
    private float tempX, tempY, tempZ;
    private double gTotal, total, lTotal;
    private long lastUpdate = 0;
    private float alpha = 0.8f;
    TextView tvgXaxis, tvgYaxis, tvgZaxis, tvXaxis, tvYaxis, tvZaxis, tvlXaxis, tvlYaxis, tvlZaxis, tvgTotal, tvTotal, tvlTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvgXaxis = (TextView)findViewById(R.id.tvgXaxis);
        tvgYaxis = (TextView)findViewById(R.id.tvgYaxis);
        tvgZaxis = (TextView)findViewById(R.id.tvgZaxis);
        tvXaxis = (TextView)findViewById(R.id.tvXaxis);
        tvYaxis = (TextView)findViewById(R.id.tvYaxis);
        tvZaxis = (TextView)findViewById(R.id.tvZaxis);
        tvlXaxis = (TextView)findViewById(R.id.tvlXaxis);
        tvlYaxis = (TextView)findViewById(R.id.tvlYaxis);
        tvlZaxis = (TextView)findViewById(R.id.tvlZaxis);
        tvgTotal = (TextView)findViewById(R.id.tvgTotal);
        tvTotal  = (TextView)findViewById(R.id.tvTotal);
        tvlTotal = (TextView)findViewById(R.id.tvlTotal);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linearAccelerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, linearAccelerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == accelerSensor) {
            //include gravity
            gAccX = event.values[0];
            gAccY = event.values[1];
            gAccZ = event.values[2];

            tempX = alpha * tempX + (1 - alpha) * event.values[0];
            tempY = alpha * tempY + (1 - alpha) * event.values[1];
            tempZ = alpha * tempZ + (1 - alpha) * event.values[2];

            accX = event.values[0] - tempX;
            accY = event.values[1] - tempY;
            accZ = event.values[2] - tempZ;

            gTotal = Math.sqrt(Math.pow(gAccX,2) + Math.pow(gAccY, 2) + Math.pow(gAccZ , 2));
            total = Math.sqrt(Math.pow(accX,2) + Math.pow(accY, 2) + Math.pow(accZ , 2));

            tvgXaxis.setText("X axis : " + String.format("%.2f", gAccX));
            tvgYaxis.setText("Y axis : " + String.format("%.2f", gAccY));
            tvgZaxis.setText("Z axis : " + String.format("%.2f", gAccZ));
            tvgTotal.setText("Total Gravity : "  + String.format("%.2f", gTotal) + " m/s\u00B2");

            tvXaxis.setText("X axis : " + String.format("%.2f", accX));
            tvYaxis.setText("Y axis : " + String.format("%.2f", accY));
            tvZaxis.setText("Z axis : " + String.format("%.2f", accZ));
            tvTotal.setText("Total Gravity : "  + String.format("%.2f", total) + " m/s\u00B2");
        }

        if(event.sensor == linearAccelerSensor) {
            //exclude gravity
            lAccX = event.values[0];
            lAccY = event.values[1];
            lAccZ = event.values[2];

            lTotal = Math.sqrt(Math.pow(lAccX,2) + Math.pow(lAccY, 2) + Math.pow(lAccZ , 2));

            tvlXaxis.setText("X axis : " + String.format("%.2f", lAccX));
            tvlYaxis.setText("Y axis : " + String.format("%.2f", lAccY));
            tvlZaxis.setText("Z axis : " + String.format("%.2f", lAccZ));
            tvlTotal.setText("Total Gravity : "  + String.format("%.2f", lTotal) + " m/s\u00B2");


        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}