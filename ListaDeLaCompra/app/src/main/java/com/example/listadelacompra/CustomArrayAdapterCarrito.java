package com.example.listadelacompra;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomArrayAdapterCarrito  extends ArrayAdapter<Producte> {
    LayoutInflater layoutInflater;
    List<Producte> llistaProductes;
    Boolean clicked = false;





    public CustomArrayAdapterCarrito(Context context, List<Producte>productes){
        super(context,0,productes);
        layoutInflater = LayoutInflater.from(context);
        llistaProductes = productes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        ImageView avatar;

        if (convertView == null)   {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.item_carro, null);
            // Els texts
            holder.setTextViewTitle( convertView.findViewById(R.id.producte_nom));
            // La foto
            holder.setImageView(convertView.findViewById(R.id.producte_avatar));
            holder.setTextViewQuantitat( convertView.findViewById(R.id.quantitat));
            holder.setLayoutProducte(convertView.findViewById(R.id.fondocarro));

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }
        // To show the picture of the product: Glide
        avatar = (ImageView) convertView.findViewById(R.id.producte_avatar);
        Producte producte = getItem(position);
        Glide.with(getContext()).load(producte.getFoto()).into(avatar);

        holder.getTextViewTitle().setText(producte.getNom());

        holder.getImageView();

        // Per gestionar la quantitat des dels botons
        holder.getTextViewQuantitat().setText(String.valueOf(producte.getQuantitat()));
        RelativeLayout fondoProducteCarro = holder.getLayoutProducte();

        holder.getLayoutProducte().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sols si està activat el checkbox
                if(!clicked){
                    fondoProducteCarro.setBackgroundColor(Color.BLUE);
                    clicked = true;
                }
                else {
                    fondoProducteCarro.setBackgroundColor(Color.WHITE);
                    clicked = false;
                }
            }
        });



        return convertView;
    }

    static class Holder {
        TextView textViewTitle;
        ImageView foto;
        TextView textViewQuantitat;

        RelativeLayout layoutProducte;

        public RelativeLayout getLayoutProducte() {
            return layoutProducte;
        }

        public void setLayoutProducte(RelativeLayout layoutProducte) {
            this.layoutProducte = layoutProducte;
        }

        public TextView getTextViewQuantitat() {
            return textViewQuantitat;
        }
        public void setTextViewQuantitat(TextView textViewQuantitat) {
            this.textViewQuantitat = textViewQuantitat;
        }
        public TextView getTextViewTitle()  {
            return textViewTitle;
        }
        public void setTextViewTitle(TextView textViewTitle)    {
            this.textViewTitle = textViewTitle;
        }

        public ImageView  getImageView () {
            return foto;
        }
        public void setImageView ( ImageView foto)     {
            this.foto = foto;
        }
    }
}

