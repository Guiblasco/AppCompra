package com.example.listadelacompra;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MainMenu {

    FirebaseDatabase database;
    DatabaseReference myRef;
    static List<Producte> llistaProductes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Carreguem la llista de productes al principi
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Productes");

        Log.d("AAA", "database.getReference");
        llistaProductes = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("AAA", "onDataChange");

                for (DataSnapshot producteSnapshot : snapshot.getChildren()) {
                    String nom = producteSnapshot.child("nom").getValue().toString();
                    String quan = producteSnapshot.child("quantitat").getValue().toString();
                    String url = producteSnapshot.child("foto").getValue().toString();
                    int quantitat = Integer.parseInt(quan);

                    Log.d("AAA", "Nom = " + nom);
                    Producte producte = new Producte(nom,quantitat ,url);
                    llistaProductes.add(producte);
                }


                for (Producte producte : llistaProductes) {
                    Log.d("AAA", "MainActivity noms = " + producte.getNom());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}