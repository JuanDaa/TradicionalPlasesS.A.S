package com.senasoft.com.tradicionalplases.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.senasoft.com.tradicionalplases.R;
import com.senasoft.com.tradicionalplases.adapter.ListaCartaAdapter;
import com.senasoft.com.tradicionalplases.adapter.ListaPedidoAdapter;
import com.senasoft.com.tradicionalplases.cad.RestauranteCAD;
import com.senasoft.com.tradicionalplases.modelo.CartaModel;
import com.senasoft.com.tradicionalplases.modelo.PedidoModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pedido extends Fragment {
    private ListView listaPedido;
    private Button btnSincroPedido;
    private TextView Valor;
    private ListaPedidoAdapter adapter;
    private ArrayList<PedidoModel> list= new ArrayList<>();
    private RestauranteCAD restauranteCAD;
    private int total=0;
    public Pedido() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pedido, container, false);
        restauranteCAD= new RestauranteCAD();
        listaPedido= (ListView) view.findViewById(R.id.listaPedido);
        btnSincroPedido= (Button) view.findViewById(R.id.btnSincroPedido);
        btnSincroPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<list.size();i++) {
                    restauranteCAD.insertarPedidoNube(getActivity(),list.get(i).getId(),list.get(i).getCantidad());
                }
            }
        });
        Valor= (TextView) view.findViewById(R.id.txtTotal);
        list= restauranteCAD.consultarPedido(getActivity());
        adapter= new ListaPedidoAdapter(getActivity(),list);
        listaPedido.setAdapter(adapter);
        for (int i=0;i<list.size();i++){
            total=total+list.get(i).getTotal();
        }
        Valor.setText("$"+total);
        return view;

    }

}
