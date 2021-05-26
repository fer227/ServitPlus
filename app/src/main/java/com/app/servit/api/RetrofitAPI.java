package com.app.servit.api;

import com.app.servit.modelos.Categoria;
import com.app.servit.modelos.Producto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/categorias")
    Call<List<Categoria>> getCategorias();

    @GET("/canciones/{id}")
    Call<List<Producto>> getProductosByCategoria(@Path("id") int id);
}
