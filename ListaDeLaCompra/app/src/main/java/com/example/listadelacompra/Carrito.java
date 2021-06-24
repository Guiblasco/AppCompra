package com.example.listadelacompra;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Carrito extends MainMenu {

    ListView listProductes;
     static List<Producte> llistaProductesCarrito;
     RelativeLayout fondoProducte;
    TextView productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        llistaProductesCarrito = new ArrayList<>();
        listProductes = findViewById(R.id.listCarro);

        PlenarCarro();
    }
    public void PlenarCarro(){
        Bundle extras = getIntent().getExtras();
        int num = extras.getInt("quant");
        ArrayList<String> nom = new ArrayList<>();
        ArrayList<String> foto = new ArrayList<>();
        ArrayList<Integer> uni = new ArrayList<>();
        for (int i = 0; i < num;i++) {
            nom.add(extras.getString("Nom"+i));
            foto.add(extras.getString("foto"+i));
            uni.add(extras.getInt("quantitat"+i));
            Log.e("obj", "cargar carrito "+nom.get(i));
        }
        for (int i = 0; i < nom.size();i++) {
            Producte p = new Producte(nom.get(i), uni.get(i),foto.get(i));

            llistaProductesCarrito.add(p);
        }

        listProductes.setAdapter(new CustomArrayAdapterCarrito(this, llistaProductesCarrito));

    }
}