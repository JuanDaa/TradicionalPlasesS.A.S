package com.senasoft.com.tradicionalplases.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.senasoft.com.tradicionalplases.R;
import com.senasoft.com.tradicionalplases.adapter.ListaCartaAdapter;
import com.senasoft.com.tradicionalplases.cad.RestauranteCAD;
import com.senasoft.com.tradicionalplases.modelo.CartaModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Carta extends Fragment {
    private ListView listaCarta;
    private Button btnSincroCarta;
    private ListaCartaAdapter adapter;
    private ArrayList<CartaModel> list= new ArrayList<>();
    private RestauranteCAD restauranteCAD;
    public Carta() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_carta, container, false);
            restauranteCAD=new RestauranteCAD();
        listaCarta = (ListView) view.findViewById(R.id.listaCarta);
        adapter=new ListaCartaAdapter(getActivity(),list);
        list=restauranteCAD.consultarCarta(getActivity());
        listaCarta.setAdapter(adapter);
        btnSincroCarta = (Button) view.findViewById(R.id.btnSincroCarta);
        btnSincroCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restauranteCAD.peticionPlatos(getActivity(),"");
            }
        });
        if (list.size()>0) {
            adapter=new ListaCartaAdapter(getActivity(),list);
            list=restauranteCAD.consultarCarta(getActivity());
            listaCarta.setAdapter(adapter);
        }else {
            Toast.makeText(getActivity(),"Debes singronizar el menu\n por favor verifica tu conexion",Toast.LENGTH_LONG).show();
        }
        return view;
    }

}
