package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.api.RetrofitService;
import com.app.servit.modelos.Pedido;
import com.app.servit.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterCuenta extends RecyclerView.Adapter<ListAdapterCuenta.ViewHolder>{
    private List<Pedido> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterCuenta(List<Pedido> datos, Context context) {
        this.datos = datos;
        this.context = context;
        this.inflador = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount(){
        return datos.size(); }

    @Override
    public ListAdapterCuenta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflador.inflate(R.layout.cuenta_element, null);
        return new ListAdapterCuenta.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterCuenta.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre_producto_carrito;
        TextView cantidad;

        ViewHolder(View itemView){
            super(itemView);
            nombre_producto_carrito = itemView.findViewById(R.id.nombre_producto_cuenta);
            cantidad = itemView.findViewById(R.id.cantidad_cuenta);
        }

        void bindData(final Pedido item){
            String cant = "x" + String.valueOf(item.getCantidad());
            cantidad.setText(cant);
            String np = item.getNombre() + " (" + String.valueOf(item.getPrecio()) + "€)";
            nombre_producto_carrito.setText(np);
        }
    }
}
