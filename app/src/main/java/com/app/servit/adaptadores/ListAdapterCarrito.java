package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.fragments.CartaFragment;
import com.app.servit.modelos.Categoria;
import com.app.servit.modelos.Pedido;
import com.app.servit.modelos.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListAdapterCarrito extends RecyclerView.Adapter<ListAdapterCarrito.ViewHolder>{
    private List<Pedido> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterCarrito(List<Pedido> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
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
                //eliminar carrito
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
