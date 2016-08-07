package com.example.israeelbr.reto2;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    RelativeLayout bacground;
    Button activate;
    private SensorManager sensorm;
    private Sensor s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bacground = (RelativeLayout) findViewById(R.id.fondo);
        activate = (Button) findViewById(R.id.btnActivar);

        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorm= (SensorManager) getSystemService(SENSOR_SERVICE);
                s = sensorm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

                sensorm.registerListener(MainActivity.this, s, SensorManager.SENSOR_DELAY_NORMAL);

                activate.setText("Sensor de proximidad activado");
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor = Float.parseFloat(String.valueOf(event.values[0]));
        if (valor <= 0 ){
            bacground.setBackgroundColor(Color.BLACK);
        }
        else{
            bacground.setBackgroundColor(Color.RED);
        }
    }


}