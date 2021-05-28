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
import com.app.servit.adaptadores.ListAdapterCuenta;
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
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CuentaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Pedido> pedidos = new ArrayList();
    static ListAdapterCuenta adapterCuenta;
    View view;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CuentaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void ControlCuenta(){
        RetrofitService.getInstance().getCuenta().enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.body().isEmpty()){
                    view.findViewById(R.id.include_cuenta_no_productos).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.include_cuenta_productos).setVisibility(View.GONE);
                }
                else{
                    pedidos.clear();
                    pedidos.addAll(response.body());
                    float precio_total = 0;
                    for(Pedido p: pedidos){
                        precio_total += p.getPrecio() * p.getCantidad();
                    }
                    ((TextView)view.findViewById(R.id.precio_cuenta)).setText("Precio total: " + precio_total + "€");
                    adapterCuenta.notifyDataSetChanged();
                    view.findViewById(R.id.include_cuenta_no_productos).setVisibility(View.GONE);
                    view.findViewById(R.id.include_cuenta_productos).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Utils.enviarToast("No se pudo traer la cuenta", getContext());
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        this.ControlCuenta();

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
        this.view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        adapterCuenta = new ListAdapterCuenta(pedidos, getContext());
        RecyclerView recyclerView2 = view.findViewById(R.id.lista_cuenta);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(adapterCuenta);

        return this.view;
    }
}