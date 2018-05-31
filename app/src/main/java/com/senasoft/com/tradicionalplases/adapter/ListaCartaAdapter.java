package com.senasoft.com.tradicionalplases.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senasoft.com.tradicionalplases.DetallePlato;
import com.senasoft.com.tradicionalplases.R;
import com.senasoft.com.tradicionalplases.db.RestauranteDB;
import com.senasoft.com.tradicionalplases.modelo.CartaModel;

import java.util.ArrayList;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class ListaCartaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CartaModel> list;

    public ListaCartaAdapter(Context context, ArrayList<CartaModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(list.get(position).getId());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
            view= LayoutInflater.from(context).inflate(R.layout.list_carta,parent,false);
        final TextView nombre,precio;
        LinearLayout linearLayout;
        linearLayout= (LinearLayout) view.findViewById(R.id.clickCarta);
        nombre= (TextView) view.findViewById(R.id.txtNombreList);
        precio= (TextView) view.findViewById(R.id.txtPrecioList);
        final String nom=list.get(position).getNombre();
        final String desc=list.get(position).getDescripcion();
        final String pre=list.get(position).getPrecio();
        final String imagen=list.get(position).getImagen();
        final String id=list.get(position).getId();
        final int idE= Integer.parseInt(id);
        nombre.setText(nom);
        precio.setText("$"+pre);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetallePlato.class);
                intent.putExtra(RestauranteDB.Tablas.NOMBRE_TBL_PLATOS,nom);
                intent.putExtra(RestauranteDB.Tablas.DECRIPCION_TBL_PLATOS,desc);
                intent.putExtra(RestauranteDB.Tablas.PRECIO_TBL_PLATOS,pre);
                intent.putExtra(RestauranteDB.Tablas.IMAGEN_TBL_PLATOS,imagen);
                intent.putExtra("id",idE);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
