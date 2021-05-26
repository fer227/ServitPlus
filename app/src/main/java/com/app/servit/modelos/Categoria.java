package com.app.servit.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categoria {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    public Categoria(String id, String nombre, String v) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
