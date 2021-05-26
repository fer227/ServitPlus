package com.app.servit.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.servit.R;
import com.app.servit.adaptadores.ListAdapterCategorias;
import com.app.servit.adaptadores.ListAdapterProductos;
import com.app.servit.api.RetrofitService;
import com.app.servit.modelos.Categoria;
import com.app.servit.modelos.Producto;
import com.app.servit.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartaFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CartaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Categoria> categorias = new ArrayList();
    private List<Producto> productos = new ArrayList();
    static ListAdapterCategorias adapterCategorias;
    static ListAdapterProductos adapterProductos;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartaFragment newInstance(String param1, String param2) {
        CartaFragment fragment = new CartaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CartaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        RetrofitService.getInstance().getCategorias().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                categorias.clear();

                categorias.addAll(response.body());
                adapterCategorias.notifyDataSetChanged();
                Utils.enviarToast("Categorías recibidas", getContext());
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Utils.enviarToast("Error al intentar recibir la carta", getContext());
                System.out.println(t.toString());
            }
        });
    }

    public void getProductos(String id) {
        System.out.println("--------------------");
        RetrofitService.getInstance().getProductosByCategoria(id).enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                productos.clear();
                System.out.println(response.body());
                productos.addAll(response.body());
                adapterProductos.notifyDataSetChanged();
                System.out.println(productos);
                Utils.enviarToast("Productos recibidos recibidas", getContext());
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Utils.enviarToast("Error al intentar recibir los productos", getContext());
                System.out.println(t.toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carta, container, false);

        adapterCategorias = new ListAdapterCategorias(categorias, this, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.include_categorias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterCategorias);

        adapterProductos = new ListAdapterProductos(productos, getContext());
        RecyclerView recyclerView2 = view.findViewById(R.id.include_productos);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(adapterProductos);

        return view;
    }
}