package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.api.RetrofitService;
import com.app.servit.fragments.CarritoFragment;
import com.app.servit.fragments.CartaFragment;
import com.app.servit.modelos.Categoria;
import com.app.servit.modelos.Pedido;
import com.app.servit.modelos.Producto;
import com.app.servit.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterCarrito extends RecyclerView.Adapter<ListAdapterCarrito.ViewHolder>{
    private List<Pedido> datos;
    private LayoutInflater inflador;
    private Context context;
    private CarritoFragment carrito;

    public ListAdapterCarrito(List<Pedido> elementos, Context context, CarritoFragment carrito){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.carrito = carrito;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
        return datos.size(); }

    @Override
    public ListAdapterCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflador.inflate(R.layout.carrito_element, null);
        return new ListAdapterCarrito.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterCarrito.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService.getInstance().borrarDelCarrito(datos.get(position).getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        carrito.ControlCarrito();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Utils.enviarToast("No se pudo eliminar del carrito", context);
                    }
                });
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre_producto_carrito;
        TextView cantidad;
        FloatingActionButton fab;

        ViewHolder(View itemView){
            super(itemView);
            nombre_producto_carrito = itemView.findViewById(R.id.nombre_producto_carrito);
            fab = itemView.findViewById(R.id.eliminar_carrito);
            cantidad = itemView.findViewById(R.id.cantidad_carrito);
        }

        void bindData(final Pedido item){
            String cant = "x" + String.valueOf(item.getCantidad());
            cantidad.setText(cant);
            String np = item.getNombre() + " (" + String.valueOf(item.getPrecio()) + "€)";
            nombre_producto_carrito.setText(np);
        }
    }
}
