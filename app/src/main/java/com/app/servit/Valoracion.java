package com.app.servit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.servit.api.RetrofitService;
import com.app.servit.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Valoracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.valoracion);
        Button enviar_valoracion = findViewById(R.id.boton_fin);

        enviar_valoracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService.getInstance().vaciarPedidos().enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Utils.enviarToast("No se ha podido reiniciar el servidor", getApplicationContext());
                    }
                });

                RetrofitService.getInstance().vaciarCarrito().enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Utils.enviarToast("No se ha podido reiniciar el servidor", getApplicationContext());
                    }
                });

                startActivity(new Intent(Valoracion.this, MainActivity.class));
            }
        });
    }
}