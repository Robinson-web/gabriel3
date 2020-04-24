package com.example.gabriel.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexi extends SQLiteOpenHelper {

    private static final String database = "bd_registros";
    private static final int version = 1;
    private static final String tabla1 = "create table registro_pedidos(id integer primary key autoincrement, nombre text, telefono text, direccion text, descripcion text, valor text, feentre text, estado integer, fecharegistro text)";


    public Conexi(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("ALTER TABLE registro_pedidos ADD COLUMN estado integer");
        //, fechae text, estado integer, fecha_ingreso DEFAULT CURRENT_TIMESTAMP
    }
}
