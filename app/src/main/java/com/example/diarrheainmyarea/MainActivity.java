package com.example.diarrheainmyarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button hospital,government;
    private CardView hospitalCard,governmentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            hospital=(Button)findViewById(R.id.hospital_login);
            government=(Button)findViewById(R.id.government_login);
            hospitalCard = findViewById(R.id.hospital_login_card);
            governmentCard = findViewById(R.id.government_login_card);

            hospitalCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Hospital_login.class));
                }
            });

            governmentCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, government_login.class));
                }
            });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Hospital_login.class));
            }
        });

        government.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, government_login.class));
            }
        });
    }
}
