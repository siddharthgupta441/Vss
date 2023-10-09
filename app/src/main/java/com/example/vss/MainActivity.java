package com.example.vss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_home())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_home()).commit();
        if(item.getItemId() == R.id.nav_aboutUs)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_about_us()).commit();
        if(item.getItemId() == R.id.nav_contactUs)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_contact_us()).commit();
        if(item.getItemId() == R.id.nav_washing)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_washing()).commit();
        if(item.getItemId() == R.id.nav_servicing)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_servicing()).commit();
        if(item.getItemId() == R.id.nav_login)
            Toast.makeText(this, "working on it", Toast.LENGTH_SHORT).show();
        return true;
    }
}