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
        listProductes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Listener per seleccionar / deseleccionar productes
                fondoProducte = (RelativeLayout)findViewById(R.id.fondocarro);
                productName = (TextView) view.findViewById(R.id.producte_nom);

                if (llistaProductesCarrito.get(position)!= null) {// Select a product

                   productName.setTextColor(getResources().getColor(R.color.red));
                    fondoProducte.setBackgroundColor(Color.CYAN);



               /*     Log.d("BBB","position "+ position + "nom del producte " + llistaProductesCarrito.get(position).getNom());
                    llistaProductesCarrito.remove(position);
                    Log.d("BBB","position "+ position + "nom del producte " + llistaProductesCarrito.get(position).getNom());


                    listProductes.setAdapter(new CustomArrayAdapterCarrito(getApplicationContext(), llistaProductesCarrito));*/



                }
            }
        });
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