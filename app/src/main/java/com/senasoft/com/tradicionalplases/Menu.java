package com.senasoft.com.tradicionalplases;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.senasoft.com.tradicionalplases.fragment.Carta;
import com.senasoft.com.tradicionalplases.fragment.Pedido;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn1= (ImageButton) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2= (ImageButton) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3= (ImageButton) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4= (ImageButton) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        Bundle bundle=getIntent().getExtras();
        if(bundle.getBoolean("intent")){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new Pedido()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new Carta()).commit();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                color(0);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new Carta()).commit();
                break;
            case R.id.btn2:
                color(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new Pedido()).commit();
                break;
            case R.id.btn3:
                color(2);
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("Bloqueado")
                        .setMessage("Este es espacio es solo para personal autorizado")
                        .create()
                        .show();
                break;
            case R.id.btn4:
                finish();
                break;
        }
    }
    private void color(int c){
        switch (c) {
            case 0:
                btn1.setBackground(getResources().getDrawable(R.drawable.boton_on));
                btn2.setBackground(getResources().getDrawable(R.drawable.boton_off));
                btn3.setBackground(getResources().getDrawable(R.drawable.boton_off));
                break;
            case 1:
                btn1.setBackground(getResources().getDrawable(R.drawable.boton_off));
                btn2.setBackground(getResources().getDrawable(R.drawable.boton_on));
                btn3.setBackground(getResources().getDrawable(R.drawable.boton_off));
                break;
            case 2:
                btn1.setBackground(getResources().getDrawable(R.drawable.boton_off));
                btn2.setBackground(getResources().getDrawable(R.drawable.boton_off));
                btn3.setBackground(getResources().getDrawable(R.drawable.boton_on));
                break;
        }
    }


}
