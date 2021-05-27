package com.app.servit.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.modelos.Ingrediente;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ListAdapterIngredientes extends RecyclerView.Adapter<ListAdapterIngredientes.ViewHolder>{
    private List<Ingrediente> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterIngredientes(List<Ingrediente> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
        return datos.size();
    }

    @Override
    public ListAdapterIngredientes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflador.inflate(R.layout.chip, null);
        return new ListAdapterIngredientes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterIngredientes.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
        holder.ingrediente.setText(datos.get(position).getNombre());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Chip ingrediente;

        ViewHolder(View itemView){
            super(itemView);
            ingrediente = itemView.findViewById(R.id.chip);
        }

        void bindData(final Ingrediente item){
            ingrediente.setText(item.getNombre());
        }
    }
}
