package com.senasoft.com.tradicionalplases;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.senasoft.com.tradicionalplases.cad.RestauranteCAD;
import com.senasoft.com.tradicionalplases.db.RestauranteDB;

public class DetallePlato extends AppCompatActivity {
    private TextView nom, desc;
    private ImageView imageView;
    private EditText cantidad;
    private Button sync;
    private int id = 0;
    private RestauranteCAD restauranteCAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detalles del menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        restauranteCAD = new RestauranteCAD();
        nom = (TextView) findViewById(R.id.txtTituloPlato);
        desc = (TextView) findViewById(R.id.txtDescripcionPlato);
        imageView = (ImageView) findViewById(R.id.imgDetalle);
        cantidad = (EditText) findViewById(R.id.editPedido);
        sync = (Button) findViewById(R.id.btnAgPedido);

        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString(RestauranteDB.Tablas.NOMBRE_TBL_PLATOS);
        String descripcion = bundle.getString(RestauranteDB.Tablas.DECRIPCION_TBL_PLATOS);
        String imagen = bundle.getString(RestauranteDB.Tablas.IMAGEN_TBL_PLATOS);
        id = bundle.getInt("id");
        Glide.with(getApplicationContext()).load(Url.urlImg + imagen).placeholder(R.mipmap.ic_launcher).into(imageView);
        nom.setText(nombre);
        desc.setText(descripcion);

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cantidad.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Campo vacio", Toast.LENGTH_LONG).show();
                } else {
                    int valor = Integer.parseInt(cantidad.getText().toString());
                    if (valor <= 0) {
                        Toast.makeText(getApplicationContext(), "valor no valido", Toast.LENGTH_LONG).show();
                    } else {
                        if (restauranteCAD.insertarPedido(getApplicationContext(), id, cantidad.getText().toString())) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(DetallePlato.this);
                            alerta.setTitle("Guardado").setMessage("El pedido ha sido gaurtado correctamente").create().show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), Menu.class).putExtra("intent", true).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                }
                            }, 2000);
                        }


                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                startActivity(new Intent(getApplicationContext(), Menu.class).putExtra("full", true).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }

    }
}
