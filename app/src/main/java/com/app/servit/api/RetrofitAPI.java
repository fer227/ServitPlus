package com.app.servit.api;

import com.app.servit.modelos.Categoria;
import com.app.servit.modelos.Pedido;
import com.app.servit.modelos.Producto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/categorias")
    Call<List<Categoria>> getCategorias();

    @GET("/productos/{id}")
    Call<List<Producto>> getProductosByCategoria(@Path("id") String id);

    @GET("/carrito")
    Call<List<Pedido>> getCarrito();

    @GET("/pedido")
    Call<List<Pedido>> getCuenta();

    @POST("/carrito")
    @FormUrlEncoded
    Call<Void> aniadirCarrito(@Field("producto") String producto, @Field("cantidad") String cantidad);

    @POST("/pedido")
    @FormUrlEncoded
    Call<Void> confirmarCarrito();

    @DELETE("/carrito/{id}")
    Call<Void> borrarDelCarrito(@Path("id") String id);
}
