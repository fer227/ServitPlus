package com.app.servit.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.fragments.CartaFragment;
import com.app.servit.modelos.Categoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListAdapterCategorias extends RecyclerView.Adapter<ListAdapterCategorias.ViewHolder>{
    private List<Categoria> datos;
    private LayoutInflater inflador;
    private CartaFragment fragment;

    public ListAdapterCategorias(List<Categoria> elementos, CartaFragment fragment, Context context){
        this.inflador = LayoutInflater.from(context);
        this.fragment = fragment;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
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

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = datos.get(position).getId();
                String nombre = datos.get(position).getNombre();
                fragment.getProductos(id, fragment.getView(), nombre);
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
