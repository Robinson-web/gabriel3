package com.example.gabriel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.gabriel.activity.Main2Activity;
import com.example.gabriel.activity.Main3Activity;
import com.example.gabriel.conexion.Conexi;
import com.example.gabriel.interfaces.Iregistros;
import com.example.gabriel.modelo.registros;

import java.util.ArrayList;

public class DaoRegistros implements Iregistros {
    @Override
    public boolean create(registros object, Main2Activity context) {
        boolean respuesta = false;
        Conexi conexi = new Conexi(context);
        SQLiteDatabase consulta = conexi.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", object.getNombre());
        registro.put("direccion", object.getDireccion());
        registro.put("descripcion", object.getDescripcion());
        registro.put("valor", object.getValor());
        registro.put("fecha_entrega", object.getFecha());
        if (consulta.insert("registro_pedidos", null, registro) > 0) {
            respuesta = true;
            conexi.close();
        }
        return respuesta;
    }

    @Override
    public boolean modificar_da(registros object, Main2Activity contexts, int via) {
        boolean respuesta = false;
        int i = via;
        Conexi conexi = new Conexi(contexts);
        SQLiteDatabase consulta = conexi.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", object.getNombre());
        registro.put("direccion", object.getDireccion());
        registro.put("descripcion", object.getDescripcion());
        registro.put("valor", object.getValor());
        registro.put("estado", 1);
        if (consulta.update("registro_pedidos", registro, "id=" + i, null) > 0) {
            respuesta = true;
            conexi.close();
            consulta.close();
            registro.clear();
        }
        return respuesta;
    }

    @Override
    public ArrayList<registros> read(Main3Activity context) {
        //se crea el arraylist donde se van a guardar la lista de registros que se almacenaran
        ArrayList<registros> list = new ArrayList<registros>();
        //define objeto del modelo
        registros object;
        //defino la conexion
        Conexi coneSql = new Conexi(context);
        //se abre la base en modo lectura
        SQLiteDatabase lectura = coneSql.getReadableDatabase();
        //Armar la consulta de la tabla
        String consultasql = "Select * from registro_pedidos where estado=1";
        //clase cursor
        Cursor cursor = lectura.rawQuery(consultasql, null);
        //
        if (cursor.moveToFirst()) {
            do {
                object = new registros();

                object.setId_registros(Integer.parseInt(cursor.getString(0)));
                object.setNombre(cursor.getString(1));
                object.setDireccion(cursor.getString(2));
                object.setDescripcion(cursor.getString(3));
                object.setValor(cursor.getString(4));
                object.setFecha(cursor.getString(5));

                list.add(object);
            } while (cursor.moveToNext());

        }

        coneSql.close();
        return list;
    }

    //metodo de sustraccion y carga
    @Override
    public registros read_one(Context context, int id) {
        registros Registros = new registros();
        Conexi conexi = new Conexi(context);
        SQLiteDatabase database = conexi.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM registro_pedidos", null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id) {
                    Registros = new registros();
                    Registros.setNombre(cursor.getString(1));
                    Registros.setDireccion(cursor.getString(2));
                    Registros.setDescripcion(cursor.getString(3));
                    Registros.setValor(cursor.getString(4));
                    Registros.setFecha(cursor.getString(5));
                }
            } while (cursor.moveToNext());
        }
        return Registros;
    }

    @Override
    public registros eliminar_da(Context context, int identificador) {
        int i = identificador;
        registros elida = new registros();
        Conexi conexi = new Conexi(context);
        SQLiteDatabase database = conexi.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("estado", 0);
        database.update("registro_pedidos", values, "id=" + i, null);
        //DELETE FROM registro_pedidos WHERE id=" + i + "
        return elida;
    }

}


