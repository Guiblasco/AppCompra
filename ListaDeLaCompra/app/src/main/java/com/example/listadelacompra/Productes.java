package com.example.listadelacompra;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Productes extends MainMenu {
    ListView listProductes;
    List<Producte> llistaEnviar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llista_productes);
        llistaEnviar = new ArrayList<>();

        listProductes = (ListView) findViewById(R.id.list);

        for (Producte producte : MainActivity.llistaProductes) {
            Log.d("AAA", "ListViewActivity noms = " + producte.getNom());
        }
        listProductes.setAdapter(new CustomArrayAdapterProductes(this, MainActivity.llistaProductes));
    }

    public void comprar(View view) {

        Intent intent = new Intent(this,Carrito.class);

        for (int x = 0; x < MainActivity.llistaProductes.size(); x ++){
            if(MainActivity.llistaProductes.get(x).getChecked()){
                Producte p =new Producte(MainActivity.llistaProductes.get(x).getNom(),MainActivity.llistaProductes.get(x).getQuantitat(),MainActivity.llistaProductes.get(x).getFoto());
                llistaEnviar.add(p);
            }
        }
        int q = llistaEnviar.size();
        intent.putExtra("quant", q);
        for (int i = 0; i < llistaEnviar.size();i++) {
            intent.putExtra("Nom" + i, llistaEnviar.get(i).getNom());
            intent.putExtra("foto" + i, llistaEnviar.get(i).getFoto());
            intent.putExtra("quantitat" + i, llistaEnviar.get(i).getQuantitat());
            Log.e("obj", "on click "+ llistaEnviar.get(i).getNom() + " unit " + llistaEnviar.get(i).getQuantitat());
        }
        for (int j = 0; j < llistaEnviar.size();j++){
           llistaEnviar.remove(j);
        }
        listProductes.setAdapter(new CustomArrayAdapterProductes(this, MainActivity.llistaProductes));

        startActivity(intent);
    }



}


