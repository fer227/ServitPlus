package com.app.servit.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alergia {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("numero")
    @Expose
    private int numero;

    public Alergia(String id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Alergia{" +
                "id='" + id + '\'' +
                ", numero=" + numero +
                '}';
    }
}
