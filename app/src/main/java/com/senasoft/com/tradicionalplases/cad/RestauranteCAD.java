package com.senasoft.com.tradicionalplases.cad;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.senasoft.com.tradicionalplases.Menu;
import com.senasoft.com.tradicionalplases.Url;
import com.senasoft.com.tradicionalplases.db.RestauranteDB;
import com.senasoft.com.tradicionalplases.fragment.Carta;
import com.senasoft.com.tradicionalplases.fragment.Pedido;
import com.senasoft.com.tradicionalplases.modelo.CartaModel;
import com.senasoft.com.tradicionalplases.modelo.PedidoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class RestauranteCAD  {
    private RequestQueue requestQueue;
    private SQLiteDatabase database;
    private RestauranteDB db;
    private ContentValues values;
    private Cursor cursor;
    private boolean inserto=false;

    public void peticionPlatos(final Context context, final String id){
        requestQueue= Volley.newRequestQueue(context);
        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url.url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            JSONObject object=jsonArray.getJSONObject(i);
                            String nombre=object.getString("nombre");
                            String descripcion=object.getString("descripcion");
                            String precio=object.getString("precio");
                            String imagen=object.getString("imagen");
                            String id=object.getString("id");
                            insertCarta(context,nombre,descripcion,precio,imagen,id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(id.equals("full")){
                        Thread.sleep(4000);
                        Intent intent = new Intent(context, Menu.class);
                        intent.putExtra("intent",false);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,"Sin conexion a internet",Toast.LENGTH_LONG).show();
                try {
                    if(id.equals("full")){
                        Thread.sleep(4000);
                        Intent intent = new Intent(context, Menu.class);
                        intent.putExtra("intent",false);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void insertCarta(Context context, String nombre, String descripcion, String precio, String imagen, String id) {
        db=new RestauranteDB(context);
        database=db.getWritableDatabase();
        values=new ContentValues();
        values.put(RestauranteDB.Tablas._ID,id);
        values.put(RestauranteDB.Tablas.NOMBRE_TBL_PLATOS,nombre);
        values.put(RestauranteDB.Tablas.DECRIPCION_TBL_PLATOS,descripcion);
        values.put(RestauranteDB.Tablas.PRECIO_TBL_PLATOS,precio);
        values.put(RestauranteDB.Tablas.IMAGEN_TBL_PLATOS,imagen);
        long result=database.insert(RestauranteDB.Tablas.TBL_PLATOS,null,values);
        if (result>0){
            database.close();
        }else {
            inserto=true;
            modificarCarta(context,nombre,descripcion,precio,imagen,id);
            database.close();
        }
        if (inserto){
            Toast.makeText(context,"Datos ya sincronizado",Toast.LENGTH_LONG).show();
        }
    }
    public ArrayList<CartaModel> consultarCarta(Context context){
        ArrayList<CartaModel> list =new ArrayList<>();
        db=new RestauranteDB(context);
        database=db.getReadableDatabase();
        String[] datos={RestauranteDB.Tablas.NOMBRE_TBL_PLATOS,
                RestauranteDB.Tablas.DECRIPCION_TBL_PLATOS, RestauranteDB.Tablas.PRECIO_TBL_PLATOS,
                RestauranteDB.Tablas.IMAGEN_TBL_PLATOS,RestauranteDB.Tablas._ID};
        cursor=database.query(RestauranteDB.Tablas.TBL_PLATOS,datos,null,null,null,null,null);
        int fila=cursor.getCount();
        for (int i=0;i<fila;i++){
            cursor.moveToNext();
            list.add(new CartaModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        }
        database.close();
        return list;
    }
    public ArrayList<PedidoModel> consultarPedido(Context context){
        ArrayList<PedidoModel> list =new ArrayList<>();
        db=new RestauranteDB(context);
        database=db.getReadableDatabase();
        String[] datos={RestauranteDB.Tablas.TBL_PLATOS+"."+RestauranteDB.Tablas.NOMBRE_TBL_PLATOS,
                RestauranteDB.Tablas.TBL_PLATOS+"."+RestauranteDB.Tablas.PRECIO_TBL_PLATOS,
                RestauranteDB.Tablas.TBL_PEDIDO+"."+RestauranteDB.Tablas.ID_PLATO_TBL_PEDIDO,
                RestauranteDB.Tablas.CANTIDAD_TBL_PEDIDO,
                RestauranteDB.Tablas.TBL_PEDIDO+"."+RestauranteDB.Tablas._ID
        };
        cursor=database.query(RestauranteDB.Tablas.TBL_PLATOS +" INNER JOIN "+ RestauranteDB.Tablas.TBL_PEDIDO +" ON "+ RestauranteDB.Tablas.TBL_PLATOS+"."+ RestauranteDB.Tablas._ID+"="+ RestauranteDB.Tablas.TBL_PEDIDO+"."+ RestauranteDB.Tablas.ID_PLATO_TBL_PEDIDO,datos,null,null,null,null,null);
        int fila=cursor.getCount();
        for (int i=0;i<fila;i++){
            cursor.moveToNext();
            int cantidad=cursor.getInt(3);
            int precio=cursor.getInt(1);
            int total=precio*cantidad;
            list.add(new PedidoModel(cursor.getString(2),cursor.getString(3),total,cursor.getString(0),cursor.getString(1),cursor.getString(4)));
        }
        database.close();
        return list;
    }

    private boolean modificarCarta(Context context, String nombre, String descripcion, String precio, String imagen, String id){
        db=new RestauranteDB(context);
        database=db.getReadableDatabase();
        values=new ContentValues();
        values.put(RestauranteDB.Tablas.NOMBRE_TBL_PLATOS,nombre);
        values.put(RestauranteDB.Tablas.DECRIPCION_TBL_PLATOS,descripcion);
        values.put(RestauranteDB.Tablas.PRECIO_TBL_PLATOS,precio);
        values.put(RestauranteDB.Tablas.IMAGEN_TBL_PLATOS,imagen);
        String condicion= RestauranteDB.Tablas._ID+"="+id;
        long result= database.update(RestauranteDB.Tablas.TBL_PLATOS,values,condicion,null);
        if (result>0){
            database.close();
            return true;

        }else {
            database.close();
            return false;
        }
    }
    public boolean insertarPedido(Context context, int id, String cantidad){
        db=new RestauranteDB(context);
        database = db.getWritableDatabase();
        values=new ContentValues();
        values.put(RestauranteDB.Tablas.ID_PLATO_TBL_PEDIDO,id);
        values.put(RestauranteDB.Tablas.CANTIDAD_TBL_PEDIDO,cantidad);
        long result= database.insert(RestauranteDB.Tablas.TBL_PEDIDO,null,values);
        if (result>0){
            database.close();
            return true;

        }else {
            database.close();
            return false;
        }
    }
    public boolean eliminar(Context context,String id){
        db=new RestauranteDB(context);
        database = db.getReadableDatabase();
        String dato= RestauranteDB.Tablas._ID+"="+id;
       long result= database.delete(RestauranteDB.Tablas.TBL_PEDIDO,dato,null);
        if (result>0){
            database.close();
            return true;
        }else {
            database.close();
            return false;
        }
    }
    public  void insertarPedidoNube(final Context context, final String id, final String cantidad){
        requestQueue=Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(Request.Method.POST, Url.urlInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                    Toast.makeText(context,"Datos enviados",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,"Error intentelo nuevamente",Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams(){
                HashMap<String,String> hashMap= new HashMap<>();
                hashMap.put("id_platos",id);
                hashMap.put("cantidad",cantidad);
                return hashMap;
            }
        };
requestQueue.add(request);
    }
}
