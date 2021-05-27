package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.modelos.Producto;

import java.util.List;

public class ListAdapterProductos extends RecyclerView.Adapter<ListAdapterProductos.ViewHolder> {
    private List<Producto> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterProductos(List<Producto> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
        return datos.size(); }

    @Override
    public ListAdapterProductos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflador.inflate(R.layout.producto_element, null);
        return new ListAdapterProductos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterProductos.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
        holder.producto.setText(datos.get(position).getNombre());
        ListAdapterIngredientes listAdapterIngredientes = new ListAdapterIngredientes(datos.get(position).getIngredientes(), context);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(listAdapterIngredientes);
        System.out.println("---------------");
        System.out.println(datos.get(position).getIngredientes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(datos.get(position).toString());
                //Intent intent = new Intent(context, Detalle.class);
                //intent.putExtra("id", datos.get(position).getId());
                //context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView producto;
        TextView precio;
        RecyclerView recyclerView;

        ViewHolder(View itemView){
            super(itemView);
            producto = itemView.findViewById(R.id.text_producto);
            precio = itemView.findViewById(R.id.text_precio);
            recyclerView = itemView.findViewById(R.id.lista_ingredientes);
        }

        void bindData(final Producto item){
            producto.setText(item.getNombre());
            precio.setText(String.valueOf(item.getPrecio()) + " €");
        }
    }
}
