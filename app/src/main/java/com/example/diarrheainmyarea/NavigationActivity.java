package com.example.diarrheainmyarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        getSupportActionBar().hide();
        bnv=findViewById(R.id.navigationbar);
        AppBarConfiguration abc=new AppBarConfiguration.Builder(
                R.id.realtimeFragment,R.id.statisticalFragment
        ).build();
        NavController navc= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this,navc,abc);
        NavigationUI.setupWithNavController(bnv,navc);
    }
}
