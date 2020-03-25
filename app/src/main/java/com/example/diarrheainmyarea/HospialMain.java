package com.example.diarrheainmyarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HospialMain extends AppCompatActivity {

    private Spinner diseasespinner,localityspinner;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospial_main);

        diseasespinner=(Spinner)findViewById(R.id.diseases_spinner);
        localityspinner=(Spinner)findViewById(R.id.locality_spinner);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        diseasespinnerfill();
        localityspinnerfill();
    }

    private void localityspinnerfill() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Area");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    System.out.println(dataSnapshot1.getValue());
                    String name = dataSnapshot1.getValue(String.class);
                    titleList.add(name);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HospialMain.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                localityspinner.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void diseasespinnerfill() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Diseases");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    System.out.println(dataSnapshot1.getValue());
                    String name = dataSnapshot1.getValue(String.class);
                    titleList.add(name);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HospialMain.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                diseasespinner.setAdapter(arrayAdapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
