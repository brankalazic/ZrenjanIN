package com.example.zrenjanin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.zrenjanin.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.zrenjanin.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.example.zrenjanin.R.*;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(layout.activity_user_dashboard);

        featuredRecycler = findViewById(id.featured_recycler);
        menuIcon = findViewById(id.menu_icon);

        drawerLayout = findViewById(id.drawer_layout);
        navigationView = findViewById(id.navigation_view);

        navigationDrawer();

        featuredRecycler();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //Da se aplikacija ne bi zatvorila kada se klikne back dok smo u meniju

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case id.nav_home:
                startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                return true;

            case id.nav_categories:
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
                return true;

            case id.nav_map:
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                return true;

            case id.nav_files:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;

            case id.nav_profile:
                startActivity(new Intent(getApplicationContext(),Name.class));
                return true;
        }

        return true;
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(drawable.restoran_kovac, "Restoran Kovac", "Restoran Kovač sa adresom u Aviv Parku, nudi moderan koncept uz već postojeće iskustvo."));
        featuredLocations.add(new FeaturedHelperClass(drawable.kafemat, "Kafemat", "Od prvoklasne jutarnje kafe do najfinijih kulinarskih delicija. Znate da ste na pravom putu, ako vidite naše ime"));
        featuredLocations.add(new FeaturedHelperClass(drawable.hotel_vojvodina, "Hotel Vojvodina", "Hotel Vojvodina u Zrenjaninu je smešten u samom centru grada, nadomak centralne pešačke zone, većine gradskih znamenitosti"));
        featuredLocations.add(new FeaturedHelperClass(drawable.restoran_kovac, "Restoran Kovac", "Restoran Kovač sa adresom u Aviv Parku, nudi moderan koncept uz već postojeće iskustvo."));
        featuredLocations.add(new FeaturedHelperClass(drawable.kafemat, "Kafemat", "Od prvoklasne jutarnje kafe do najfinijih kulinarskih delicija. Znate da ste na pravom putu, ako vidite naše ime"));
        featuredLocations.add(new FeaturedHelperClass(drawable.hotel_vojvodina, "Hotel Vojvodina", "Hotel Vojvodina u Zrenjaninu je smešten u samom centru grada, nadomak centralne pešačke zone, većine gradskih znamenitosti"));


        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

}
