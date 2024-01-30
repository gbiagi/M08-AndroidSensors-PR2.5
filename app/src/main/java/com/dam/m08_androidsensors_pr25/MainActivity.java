package com.dam.m08_androidsensors_pr25;

import static androidx.core.content.ContextCompat.getSystemService;

import static java.lang.String.valueOf;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    SensorEventListener sensorListener;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView posX = findViewById(R.id.posX);
        TextView posY = findViewById(R.id.posY);
        TextView posZ = findViewById(R.id.posZ);

        // Per canviar el color de fons del TextView a blanc
        posX.setBackgroundColor(getResources().getColor(android.R.color.white));
        posY.setBackgroundColor(getResources().getColor(android.R.color.white));
        posZ.setBackgroundColor(getResources().getColor(android.R.color.white));

        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Valors de l'acceleròmetre en m/s^2
                float xAcc = sensorEvent.values[0];
                float yAcc = sensorEvent.values[1];
                float zAcc = sensorEvent.values[2];

                posX.setText(valueOf(xAcc));
                posY.setText(valueOf(yAcc));
                posZ.setText(valueOf(zAcc));

                // Processament o visualització de dades...
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // Es pot ignorar aquesta CB de moment
            }
        };

        // Seleccionem el tipus de sensor (veure doc oficial)
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // registrem el Listener per capturar els events del sensor
        if( sensor!=null ) {
            sensorManager.registerListener(sensorListener,sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}

