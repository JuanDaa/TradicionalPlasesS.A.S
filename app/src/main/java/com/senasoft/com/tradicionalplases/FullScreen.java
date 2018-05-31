package com.senasoft.com.tradicionalplases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.senasoft.com.tradicionalplases.cad.RestauranteCAD;

public class FullScreen extends AppCompatActivity {
    private RestauranteCAD restauranteCAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        restauranteCAD= new RestauranteCAD();
        restauranteCAD.peticionPlatos(getApplicationContext(),"full");
      //  finish();
    }
}
