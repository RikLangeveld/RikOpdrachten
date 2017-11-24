package com.school.langevr004.kungsleden;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;

/*
Main activity, classe die de logic heeft van de startkaart en alle markers op het scherm zet.
Ook het wisselen naar de andere activities wordt in deze classe geregeld
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;

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

        //Listeners for pages
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navBar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()){
                    case(R.id.nav_overview):
                        OverviewFragment overviewFragment = new OverviewFragment();
                        fragmentTransaction.replace(R.id.mainFrame, overviewFragment, "overviewFragment");
                        fragmentTransaction.commit();
                        break;
                    case (R.id.nav_map):
                        MapFragment mapFragment = new MapFragment();
                        fragmentTransaction.replace(R.id.mainFrame, mapFragment, "mapFragment");
                        fragmentTransaction.commit();
                        break;
                    case(R.id.nav_waypoints):
                        Intent waypointActivity = new Intent(getApplicationContext(), AllCabins.class);
                        startActivity(waypointActivity);
                        break;
                    default:
                        //do nothing
                        break;

                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
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
        return super.onOptionsItemSelected(item);
    }
}

