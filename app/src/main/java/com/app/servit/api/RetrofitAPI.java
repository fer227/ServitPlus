package com.app.servit.api;

import com.app.servit.modelos.Categoria;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("/categorias")
    Call<List<Categoria>> getCategorias();
}
