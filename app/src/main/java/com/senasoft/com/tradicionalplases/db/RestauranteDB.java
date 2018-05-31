package com.senasoft.com.tradicionalplases.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class RestauranteDB extends SQLiteOpenHelper {
    private static final String name="Restaurante.db";
    private static final int version=1;
    public RestauranteDB(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tablas.QUERY_TBL_PLATOS);
        db.execSQL(Tablas.QUERY_TBL_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Tablas.DROP_TBL_PLATOS);
        db.execSQL(Tablas.DROP_TBL_PEDIDO);
        onCreate(db);
    }
    public class Tablas implements BaseColumns{
        public static final String TBL_PLATOS="platos";
        public static final String NOMBRE_TBL_PLATOS="nombre";
        public static final String DECRIPCION_TBL_PLATOS="descripcion";
        public static final String PRECIO_TBL_PLATOS="precio";
        public static final String IMAGEN_TBL_PLATOS="imagen";

        public static final String TBL_PEDIDO="pedido";
        public static final String ID_PLATO_TBL_PEDIDO="id_plato";
        public static final String CANTIDAD_TBL_PEDIDO="cantidad";

        private static final String TEXT=" TEXT";
        private static final String INTEGER=" INTEGER";
        private static final String COMA=",";
        private static final String QUERY_TBL_PLATOS="CREATE TABLE "+Tablas.TBL_PLATOS+"( "+Tablas._ID+Tablas.INTEGER+" PRIMARY KEY"+Tablas.COMA+
                Tablas.NOMBRE_TBL_PLATOS+Tablas.TEXT+Tablas.COMA+
                Tablas.DECRIPCION_TBL_PLATOS+Tablas.TEXT+Tablas.COMA+
                Tablas.PRECIO_TBL_PLATOS+Tablas.TEXT+Tablas.COMA+
                Tablas.IMAGEN_TBL_PLATOS+Tablas.TEXT+
                ")";
        private static final String QUERY_TBL_PEDIDO="CREATE TABLE "+Tablas.TBL_PEDIDO+"( "+Tablas._ID+Tablas.INTEGER+" PRIMARY KEY AUTOINCREMENT"+Tablas.COMA+
                Tablas.ID_PLATO_TBL_PEDIDO+Tablas.INTEGER+Tablas.COMA+
                Tablas.CANTIDAD_TBL_PEDIDO+Tablas.TEXT+Tablas.COMA+
                "FOREIGN KEY ("+Tablas.ID_PLATO_TBL_PEDIDO+") REFERENCES "+Tablas.TBL_PLATOS+"("+Tablas._ID+")  "+
                ")";
        private static final String DROP_TBL_PLATOS="DROP TABLE IF EXISTS "+Tablas.TBL_PLATOS;
        private static final String DROP_TBL_PEDIDO="DROP TABLE IF EXISTS "+Tablas.TBL_PEDIDO;
    }
}
