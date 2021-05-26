package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        ViewHolder(View itemView){
            super(itemView);
            producto = itemView.findViewById(R.id.text_producto);
        }

        void bindData(final Producto item){
            producto.setText(item.getNombre());
        }
    }
}
