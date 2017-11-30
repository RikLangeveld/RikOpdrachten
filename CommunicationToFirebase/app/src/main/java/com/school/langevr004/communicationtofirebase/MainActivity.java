package com.school.langevr004.communicationtofirebase;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    float xaccel, yaccel, zaccel;
    float xaccelDB, yaccelDB, zaccelDB;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference xaccellRef = mRootRef.child("xaccel");
    DatabaseReference yaccellRef = mRootRef.child("yaccel");
    DatabaseReference zaccellRef = mRootRef.child("zaccel");

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textView1);
        
        senSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview.setText(" X:" + xaccelDB + "\n Y:" + yaccelDB + "\n Z:" + zaccelDB );
            }
        });
    }

        //Runnable object that sends the X Y Z data to database every 20 seconds.
        //calls itself!
        Runnable r1 = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(20000);
                xaccellRef.setValue(xaccel);
                yaccellRef.setValue(yaccel);
                zaccellRef.setValue(zaccel);
                run();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onStart(){
        super.onStart();

        Thread t1 = new Thread(r1, "Thread t1");
        t1.start();

        xaccellRef.addValueEventListener(new ValueEventListener() {
            //als data veranderd
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                xaccelDB = dataSnapshot.getValue(Float.class);
            }
            //als je een error krijgt
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        yaccellRef.addValueEventListener(new ValueEventListener() {
            //als data veranderd
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                yaccelDB = dataSnapshot.getValue(Float.class);
            }
            //als je een error krijgt
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        zaccellRef.addValueEventListener(new ValueEventListener() {
            //als data veranderd
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                zaccelDB = dataSnapshot.getValue(Float.class);
            }
            //als je een error krijgt
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Handler myHandler = new Handler();

    public void onSensorChanged (final SensorEvent event) {
        myHandler.post(  new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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
