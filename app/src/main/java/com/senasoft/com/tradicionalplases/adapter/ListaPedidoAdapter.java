package com.senasoft.com.tradicionalplases.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.senasoft.com.tradicionalplases.DetallePlato;
import com.senasoft.com.tradicionalplases.R;
import com.senasoft.com.tradicionalplases.cad.RestauranteCAD;
import com.senasoft.com.tradicionalplases.db.RestauranteDB;
import com.senasoft.com.tradicionalplases.modelo.CartaModel;
import com.senasoft.com.tradicionalplases.modelo.PedidoModel;

import java.util.ArrayList;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class ListaPedidoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PedidoModel> list;

    public ListaPedidoAdapter(Context context, ArrayList<PedidoModel> list) {
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
        view= LayoutInflater.from(context).inflate(R.layout.lista_pedido,parent,false);
        TextView nombre,precio,cantidad;
        LinearLayout linearLayout;
        linearLayout= (LinearLayout) view.findViewById(R.id.clickPedido);
        nombre= (TextView) view.findViewById(R.id.txtNombreListP);
        precio= (TextView) view.findViewById(R.id.txtPrecioListP);
        cantidad= (TextView) view.findViewById(R.id.txtCandidadListP);
        final String nom=list.get(position).getNombre();
        final String pre=list.get(position).getPrecio();
        final String id=list.get(position).getId_pedido();
        final String canti=list.get(position).getCantidad();
        nombre.setText(nom);
        precio.setText("$"+pre);
        cantidad.setText(canti);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar").setMessage("Desea eliminar " + nom + " del pedido").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (new RestauranteCAD().eliminar(context,id)) {
                            Toast.makeText(context,"Eliminado",Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("NO",null);
                alerta.create().show();
            }

        });

        return view;
    }
}
