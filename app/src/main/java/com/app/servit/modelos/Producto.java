package com.app.servit.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Producto {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("precio")
    @Expose
    private float precio;

    @SerializedName("categoria")
    @Expose
    private String categoria;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("ingredientes")
    @Expose
    private List<Ingrediente> ingredientes;

    @SerializedName("alergias")
    @Expose
    private List<Alergia> alergias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Alergia> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Alergia> alergias) {
        this.alergias = alergias;
    }

    public Producto(String id, String nombre, float precio, String categoria, String descripcion, List<Ingrediente> ingredientes, List<Alergia> alergias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.alergias = alergias;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ingredientes=" + ingredientes +
                ", alergias=" + alergias +
                '}';
    }
}
