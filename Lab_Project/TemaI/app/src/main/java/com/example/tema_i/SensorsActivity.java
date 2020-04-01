package com.example.tema_i;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.ConsumerIrManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorsActivity extends AppCompatActivity implements SensorEventListener {

    private List<Sensor> listSensors;
    private ListView listView;
    private SensorManager smm;
    private String[] valuesSensors = new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        listView = (ListView)findViewById(R.id.listView);
        smm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<String> nameSensors = new ArrayList<String>();

        listSensors = smm.getSensorList(Sensor.TYPE_ALL);

        /*Adaugam cate un listener pentru fiecare senzor ca sa putem sa ii masuram valorile ulterior*/
        for(Sensor sens : listSensors){
            smm.registerListener(this,sens,SensorManager.SENSOR_DELAY_NORMAL);
            nameSensors.add(sens.getName());
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nameSensors){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);

                tv.setTypeface(null, Typeface.BOLD_ITALIC);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19);

                // Return the view
                return view;
            }
        };

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SensorsActivity.this,listSensors.get(position).toString()  + "\nValorile sunt:\n" + valuesSensors[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        /*Am pus valorile pentru fiecare senzor pe cate o pozitie in vectorul de stringuri valuesSensor
        Ulterior aceste valori le afisez atunci cand se face click pe un senzor din listView*/

        if( event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            valuesSensors[1] = "";
            for(float v : event.values){
                valuesSensors[1] += v + "  ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            valuesSensors[2] = "";
            for(float v : event.values){
                valuesSensors[2] += v + "  ";
            }
        }
        /*if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
           Nu mai functioneaza deoaree Sensor.TYPE_ORIENTATION nu mai este folosit in Api-ul curent
        }*/
        /*if(event.sensor.getType() == Sensor.TYPE_TEMPERATURE){
           Nu mai functioneaza deoaree Sensor.TYPE_TEMPERATURE nu mai este folosit in Api-ul curent
        }*/
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            valuesSensors[5] = "";
            for(float v : event.values){
                valuesSensors[5] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            valuesSensors[6] = "";
            for(float v : event.values){
                valuesSensors[6] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
            valuesSensors[7] = "";
            for(float v : event.values){
                valuesSensors[7] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            valuesSensors[8] = "";
            for(float v : event.values){
                valuesSensors[8] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED){
            valuesSensors[9] = "";
            for(float v : event.values){
                valuesSensors[9] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED){
            valuesSensors[10] = "";
            for(float v : event.values){
                valuesSensors[10] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR){
            valuesSensors[11] = "";
            for(float v : event.values){
                valuesSensors[11] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR){
            valuesSensors[12] = "";
            for(float v : event.values){
                valuesSensors[12] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
            valuesSensors[13] = "";
            for(float v : event.values){
                valuesSensors[13] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            valuesSensors[14] = "";
            for(float v : event.values){
                valuesSensors[14] += v + "   ";
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            valuesSensors[15] = "";
            for(float v : event.values){
                valuesSensors[15] += v + "   ";
            }
        }
    }
}
