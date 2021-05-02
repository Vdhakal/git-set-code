package com.example.git_set_code.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.git_set_code.R;

import com.example.git_set_code.fragments.DriverProfile;
import com.example.git_set_code.fragments.EditFragment;
import com.example.git_set_code.fragments.HomeFragment;
import com.example.git_set_code.fragments.MapsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;

import java.util.Map;

public class LandingActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    Toolbar toolbar;
    final String TAG="DEBUG";
    private DrawerLayout drawer;
    View view;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"On create opt menu works");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        View view = findViewById(android.R.id.content).getRootView();
        //noinspection SimplifiableIfStatement
        if (id == R.id.notification) {
            Toast.makeText(this, "You clicked on Notifications", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Intent intent = getIntent();
        navigationView = findViewById(R.id.nav_view);
        setUpNavigation();
        setupDrawerContent(navigationView);
        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.profile:
                fragment = new DriverProfile();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment ).addToBackStack("").commit();

        // Close the navigation drawer
        drawer.closeDrawers();
    }


    private void setUpNavigation(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = navHostFragment.getNavController();
//        navController.navigate(
//                RequestTransferFragmentDirections.actionRequestTransferFragmentToBlankFragment()),
//                NavOptions.Builder().setLaunchSingleTop(true).build()
//)

            if (bottomNavigationView.getSelectedItemId() == R.id.mapsFragment) {
                navController.popBackStack(R.id.mapfragment, false);
            }
            else
                NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());

        }

//        bottomNavigationView.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
//        bottomNavigationView.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_map_24));
//        bottomNavigationView.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_edit_24));
//
//        Fragment homeFragment = new HomeFragment();
//        Fragment mapFragment = new MapsFragment();
//        Fragment editFragment = new EditFragment();
//        bottomNavigationView.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
//            @Override
//            public void onClickItem(MeowBottomNavigation.Model item) {
//
//            }
//        });
//        bottomNavigationView.setOnShowListener(new MeowBottomNavigation.ShowListener() {
//            @Override
//            public void onShowItem(MeowBottomNavigation.Model item) {
//                if(item.getId() == 1){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment ).addToBackStack("").commit();
//                }
//                if(item.getId() == 2){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mapFragment ).addToBackStack("").commit();
//                }
//                if(item.getId() == 3){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, editFragment ).addToBackStack("").commit();
//                }
//            }
//        });
//
//        bottomNavigationView.show(1, true);

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}