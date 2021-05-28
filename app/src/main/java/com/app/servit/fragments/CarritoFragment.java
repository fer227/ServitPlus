package com.app.servit.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.servit.R;
import com.app.servit.adaptadores.ListAdapterCarrito;
import com.app.servit.api.RetrofitService;
import com.app.servit.modelos.Pedido;
import com.app.servit.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CarritoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Pedido> pedidos = new ArrayList();
    static ListAdapterCarrito adapterCarrito;
    View view;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CarritoFragment() {
        // Required empty public constructor
    }

    public void ControlCarrito(){
        RetrofitService.getInstance().getCarrito().enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.body().isEmpty()){
                    view.findViewById(R.id.include_carrito_no_productos).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.include_carrito_productos).setVisibility(View.GONE);
                }
                else {
                    pedidos.clear();
                    pedidos.addAll(response.body());
                    float precio_total = 0;
                    for(Pedido p: pedidos){
                        precio_total += p.getPrecio() * p.getCantidad();
                    }
                    ((TextView)view.findViewById(R.id.precio_carrito)).setText("Precio total del pedido: " + precio_total + "€");
                    adapterCarrito.notifyDataSetChanged();
                    view.findViewById(R.id.include_carrito_no_productos).setVisibility(View.GONE);
                    view.findViewById(R.id.include_carrito_productos).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Utils.enviarToast("No se ha podido obtener el carrito", getContext());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        this.ControlCarrito();

        OnBackPressedCallback callback =  new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        adapterCarrito = new ListAdapterCarrito(pedidos, getContext(), this);
        RecyclerView recyclerView2 = view.findViewById(R.id.lista_carrito);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(adapterCarrito);

        this.view = view;
        return view;
    }
}