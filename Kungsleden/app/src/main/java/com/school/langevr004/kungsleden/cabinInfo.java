package com.school.langevr004.kungsleden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class cabinInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin_info);

        Intent i = getIntent();

        TextView textView = (TextView) findViewById(R.id.textViewCabin);
        textView.setText(i.getExtras().getString("title"));

    }
}
