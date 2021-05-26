package com.app.servit.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.modelos.Categoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListAdapterCategorias extends RecyclerView.Adapter<ListAdapterCategorias.ViewHolder>{
    private List<Categoria> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterCategorias(List<Categoria> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
        System.out.println("GETITEMCOUNT" +datos.size());
        return datos.size(); }

    @Override
    public ListAdapterCategorias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflador.inflate(R.layout.categoria_element, null);
        return new ListAdapterCategorias.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterCategorias.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
        holder.categoria.setText(datos.get(position).getNombre());

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
        TextView categoria;
        FloatingActionButton fab;

        ViewHolder(View itemView){
            super(itemView);
            categoria = itemView.findViewById(R.id.text_categoria);
            fab = itemView.findViewById(R.id.button_enter_categoria);
        }

        void bindData(final Categoria item){
            categoria.setText(item.getNombre());
        }
    }
}
