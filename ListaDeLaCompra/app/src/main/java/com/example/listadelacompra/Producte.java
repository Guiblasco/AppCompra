package com.example.listadelacompra;

import android.os.Parcel;
import android.os.Parcelable;

public class Producte implements Parcelable {
    private String nom;
    private int quantitat;




    private String foto;
    private Boolean selected;
    private Boolean checked = false;
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }



    public Producte(String nom, int quantitat, String foto) {
        this.nom = nom;
        this.quantitat = quantitat;
        this.foto = foto;
        this.selected = false;
        this.checked = isChecked();
    }

    protected Producte(Parcel in) {
        nom = in.readString();
        quantitat = in.readInt();
        foto = in.readString();
        byte tmpSelected = in.readByte();
        selected = tmpSelected == 0 ? null : tmpSelected == 1;
        checked = in.readByte() != 0 ;
    }

    public static final Creator<Producte> CREATOR = new Creator<Producte>() {
        @Override
        public Producte createFromParcel(Parcel in) {
            return new Producte(in);
        }

        @Override
        public Producte[] newArray(int size) {
            return new Producte[size];
        }
    };

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public int getQuantitat() {return quantitat;}
    public void setQuantitat(int quantitat) {this.quantitat = quantitat;}
    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}
    public Boolean getSelected() {return selected;}
    public void setSelected(Boolean selected) {this.selected = selected;}
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeInt(quantitat);
        parcel.writeString(foto);
        parcel.writeByte((byte) (selected == null ? 0 : selected ? 1 : 2));
        parcel.writeByte((byte)(checked ? 1 : 0));
    }

    public boolean isChecked() {return checked;}

    public void setChecked(boolean checked) {this.checked = checked;}



}
