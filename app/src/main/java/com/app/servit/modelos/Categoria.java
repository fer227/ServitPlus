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
}
