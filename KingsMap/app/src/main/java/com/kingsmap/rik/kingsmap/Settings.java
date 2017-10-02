package com.kingsmap.rik.kingsmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {

    //private List<String> mItems;
    //private ListView mListView;
    //private ArrayAdapter<String> mAdapter;

    private Switch mSwitch_GoogleTerain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSwitch_GoogleTerain = (Switch) findViewById(R.id.switch_terain);
        mSwitch_GoogleTerain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked == false)
                {
                    MainActivity.useTerainMode = false;
                    MainActivity.mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else
                {
                    MainActivity.useTerainMode = true;
                    MainActivity.mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }
        });

        //mListView = (ListView) findViewById(R.id.ListView);
        //mItems = new ArrayList<>();
        //mItems.add("Google Terain");

        //updateUI();
    }

    /*
    private void updateUI() {
        if (mAdapter == null) {
            //Implement the adapter
            mAdapter = new ArrayAdapter<>(this, R.layout.row_item_settings, R.id.switchItem, mItems);
            mListView.setAdapter(mAdapter);
        } else {
            //Reflect changes made to the list/adapter.
            mAdapter.notifyDataSetChanged();
        }
    }
    */
}
