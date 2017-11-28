package com.school.langevr004.communicationtofirebase;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textview;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    float xaccel;
    float yaccel;
    float zaccel;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference xaccel1Ref = mRootRef.child("xaccel");
    DatabaseReference yaccellRef = mRootRef.child("yaccel");
    DatabaseReference zaccellRef = mRootRef.child("zaccel");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textview = (TextView) findViewById(R.id.textView);
        
        senSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xaccel1Ref.setValue(xaccel);
                yaccellRef.setValue(yaccel);
                zaccellRef.setValue(zaccel);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        xaccel1Ref.addValueEventListener(new ValueEventListener() {
            //als data veranderd
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float xaccel = dataSnapshot.getValue(Float.class);
                textview.setText(String.valueOf(xaccel));
            }
            //als je een error krijgt
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private class AccelWork implements Runnable {

        public void run() {
            // do something with the data to the gui
        }
    }
    private Handler myHandler = new Handler();

    public void onSensorChanged (final SensorEvent event) {
        myHandler.post(  new Runnable() {
            public void run() {
                // do something with sensordata to gui

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    xaccel = event.values[0];
                    yaccel = event.values[1];
                    zaccel = event.values[2];
                }
            }
        });
    }

}
