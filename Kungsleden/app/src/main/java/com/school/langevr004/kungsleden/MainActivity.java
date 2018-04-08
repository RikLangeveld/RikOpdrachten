package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import org.osmdroid.config.Configuration;

/*
Main activity, classe die de logic heeft van de startkaart en alle markers op het scherm zet.
Ook het wisselen naar de andere activities wordt in deze classe geregeld
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

    private boolean alreadyShowingLegenda = true; // Controleerd of de legenda al zichtbaar is.

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        //initialize drawlayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close );

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Start de app in de Map positie.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment mapFragment = new MapFragment();
        fragmentTransaction.replace(R.id.mainFrame, mapFragment);
        fragmentTransaction.addToBackStack("nav_map").commit();

        //Listeners for pages
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navBar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()){
                    case(R.id.nav_overview):
                        //ToggleLegenda(false);
                        Fragment overviewFragment = new OverviewFragment(R.layout.fragment_overview);
                        fragmentTransaction.replace(R.id.mainFrame, overviewFragment);
                        fragmentTransaction.addToBackStack("nav_overview").commit();
                        break;
                    case (R.id.nav_map):
                        //ToggleLegenda(true);
                        Fragment mapFragment = new MapFragment();
                        fragmentTransaction.replace(R.id.mainFrame, mapFragment);
                        fragmentTransaction.addToBackStack("nav_map").commit();
                        break;
                    case(R.id.nav_waypoints):
                        //ToggleLegenda(false);
                        Fragment waypointsFragment = new WaypointsFragment(R.layout.fragment_waypoints);
                        fragmentTransaction.replace(R.id.mainFrame, waypointsFragment);
                        fragmentTransaction.addToBackStack("nav_waypoints").commit();
                        break;
                    default:
                        //do nothing
                        break;

                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        final ImageButton layerButton = (ImageButton) findViewById(R.id.cabins);
        final PopupMenu popup = new PopupMenu(MainActivity.this, layerButton);
        popup.getMenuInflater().inflate(R.menu.legenda_menu, popup.getMenu());

        layerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Here
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.cabins){
                            item.setChecked(!item.isChecked());
                        }
                        //This is the trick here!!!!
                        popup.show();
                        return true;
                    }
                });
                //And here
                popup.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        /*
        switch (item.getItemId()) {
            case (R.id.cabins):
                item.setChecked(!item.isChecked());
                return false;
            case (R.id.food):
                item.setChecked(!item.isChecked());
                return false;
            case (R.id.boats):
                item.setChecked(!item.isChecked());

                return false;
        }

        */
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.legenda_menu, menu);
        return true;
    }


    /*
    Toggle de Legenda menu. Wordt niet geactiveerd als de legenda al actief is. (duhhh...)
     */
    /*
    private void ToggleLegenda(boolean activate)
    {
        if (!activate)
        {
            alreadyShowingLegenda = false;
            mToolbar.getMenu().clear();
        }
        else if (!alreadyShowingLegenda)
        {
            alreadyShowingLegenda = true;
            getMenuInflater().inflate(R.menu.legenda_menu, mToolbar.getMenu());
        }

    }
    */
}

