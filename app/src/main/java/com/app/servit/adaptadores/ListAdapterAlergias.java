package com.app.servit.adaptadores;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.servit.R;
import com.app.servit.modelos.Alergia;

import java.util.List;

public class ListAdapterAlergias extends RecyclerView.Adapter<ListAdapterAlergias.ViewHolder>{
    private List<Alergia> datos;
    private LayoutInflater inflador;
    private Context context;

    public ListAdapterAlergias(List<Alergia> elementos, Context context){
        this.inflador = LayoutInflater.from(context);
        this.context = context;
        this.datos = elementos;
    }

    @Override
    public int getItemCount(){
        return datos.size();
    }

    @Override
    public ListAdapterAlergias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflador.inflate(R.layout.alergia_element, null);
        return new ListAdapterAlergias.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterAlergias.ViewHolder holder, int position) {
        holder.bindData(datos.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image_alergia);
        }

        void bindData(final Alergia item){
            //Drawable d = Drawable.createFromPath("a" + item.getNumero() + ".xml");
            switch (item.getNumero()){
                case 1:
                    image.setBackground(context.getDrawable(R.drawable.a1));
                    break;
                case 2:
                    image.setBackground(context.getDrawable(R.drawable.a2));
                    break;

                case 3:
                    image.setBackground(context.getDrawable(R.drawable.a3));
                    break;

                case 4:
                    image.setBackground(context.getDrawable(R.drawable.a4));
                    break;

                case 5:
                    image.setBackground(context.getDrawable(R.drawable.a5));
                    break;

                case 6:
                    image.setBackground(context.getDrawable(R.drawable.a6));
                    break;

                case 7:
                    image.setBackground(context.getDrawable(R.drawable.a7));
                    break;

                case 8:
                    image.setBackground(context.getDrawable(R.drawable.a8));
                    break;

                case 9:
                    image.setBackground(context.getDrawable(R.drawable.a9));
                    break;

                case 10:
                    image.setBackground(context.getDrawable(R.drawable.a10));
                    break;

                case 11:
                    image.setBackground(context.getDrawable(R.drawable.a11));
                    break;

                case 12:
                    image.setBackground(context.getDrawable(R.drawable.a12));
                    break;

                case 13:
                    image.setBackground(context.getDrawable(R.drawable.a13));
                    break;

                case 14:
                    image.setBackground(context.getDrawable(R.drawable.a14));
                    break;


            };
        }
    }
}
