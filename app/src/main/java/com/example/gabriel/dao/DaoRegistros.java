package com.example.gabriel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gabriel.activity.Main2Activity;
import com.example.gabriel.activity.Main3Activity;
import com.example.gabriel.activity.Main4Activity;
import com.example.gabriel.conexion.Conexi;
import com.example.gabriel.interfaces.Iregistros;
import com.example.gabriel.modelo.registros;

import java.util.ArrayList;

public class DaoRegistros implements Iregistros {
int identificador;



    @Override
    public boolean create(registros object, Main2Activity context) {
        boolean respuesta = false;

        Conexi conexi = new Conexi(context);
        SQLiteDatabase consulta = conexi.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("nombre", object.getNombre());
        registro.put("telefono", object.getTelefono());
        registro.put("direccion", object.getDireccion());
        registro.put("descripcion", object.getDescripcion());
        registro.put("valor", object.getValor());
        registro.put("feentre", object.getFecha());
        registro.put("estado", object.getEstado());
        registro.put("fecharegistro", object.getFechaingreso());
        if (consulta.insert("registro_pedidos", null, registro) > 0) {
            respuesta = true;
            conexi.close();
        }
        return respuesta;
    }
    @Override
    public boolean modificar_da(registros object, Main2Activity context, int via) {
        boolean respuesta = false;
        int i = via;
        Conexi conexi = new Conexi(context);
        SQLiteDatabase consulta = conexi.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", object.getNombre());
        registro.put("telefono",object.getTelefono());
        registro.put("direccion", object.getDireccion());
        registro.put("descripcion", object.getDescripcion());
        registro.put("valor", object.getValor());
        registro.put("feentre", object.getFecha());
        if (consulta.update("registro_pedidos", registro, "id=" + i, null) > 0) {
            respuesta = true;
            conexi.close();
            consulta.close();
            registro.clear();
        }
        return respuesta;
    }

    @Override
    public boolean cambiaEstado(registros object, Main4Activity context, int id) {
        boolean lorealizo=false;

        Conexi conexion = new Conexi(context);
        SQLiteDatabase cambio = conexion.getWritableDatabase();
        ContentValues conten = new ContentValues();
        int es =object.getEstado();
        if(es == 1){
            conten.put("estado",1);
            if (cambio.update("registro_pedidos", conten, "id=" + id, null) > 0) {
                lorealizo = true;
                conexion.close();
                cambio.close();
                conten.clear();
            }

        }else{

            lorealizo=false;
        }


        return lorealizo;
    }

    @Override
    public ArrayList<registros> read(Main3Activity context) {
        ArrayList<registros> list = new ArrayList<registros>();
        registros object;
        Conexi coneSql = new Conexi(context);
        SQLiteDatabase lectura = coneSql.getReadableDatabase();
        //Armar la consulta de la tabla
        String consultasql = "Select * from registro_pedidos where estado=1 ";
        //clase cursor
        Cursor cursor = lectura.rawQuery(consultasql, null);
        if (cursor.moveToFirst()) {
            do {
                object = new registros();
                object.setId_registros(Integer.parseInt(cursor.getString(0)));
                object.setNombre(cursor.getString(1));
                object.setTelefono(cursor.getString(2));
                object.setDireccion(cursor.getString(3));
                object.setDescripcion(cursor.getString(4));
                object.setValor(cursor.getString(5));
                object.setFecha(cursor.getString(6));
                object.setEstado(Integer.parseInt(cursor.getString(7)));
                object.setFechaingreso(cursor.getString(8));
                list.add(object);
            } while (cursor.moveToNext());
        }
        coneSql.close();
        return list;
    }

    @Override
    // lectura de registros eliminados estado I
    public ArrayList<registros> read_eli(Main4Activity context) {

        ArrayList<registros> list2 = new ArrayList<registros>();
        registros object;
        Conexi coneSql = new Conexi(context);
        SQLiteDatabase lectura = coneSql.getReadableDatabase();
        //Armar la consulta de la tabla
        String consultasql = "Select * from registro_pedidos where estado= 0";
        //clase cursor
        Cursor cursor = lectura.rawQuery(consultasql, null);
        if (cursor.moveToFirst()) {
            do {
                object = new registros();
                object.setId_registros(Integer.parseInt(cursor.getString(0)));
                object.setNombre(cursor.getString(1));
                object.setTelefono(cursor.getString(2));
                object.setDireccion(cursor.getString(3));
                object.setDescripcion(cursor.getString(4));
                object.setValor(cursor.getString(5));
                object.setFecha(cursor.getString(6));
                object.setEstado(Integer.parseInt(cursor.getString(7)));
                object.setFechaingreso(cursor.getString(8));
                list2.add(object);
            } while (cursor.moveToNext());
        }
        coneSql.close();
        return list2;
    }

    //metodo de sustraccion y carga
    @Override
    public registros read_one(Context context, int id) {
        registros Registros = new registros();
        Conexi conexi = new Conexi(context);
        SQLiteDatabase database = conexi.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM registro_pedidos where estado=1", null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id) {
                    Registros = new registros();
                    Registros.setNombre(cursor.getString(1));
                    Registros.setTelefono(cursor.getString(2));
                    Registros.setDireccion(cursor.getString(3));
                    Registros.setDescripcion(cursor.getString(4));
                    Registros.setValor(cursor.getString(5));
                    Registros.setFecha(cursor.getString(6));
                    Registros.setEstado(Integer.parseInt(cursor.getString(7)));
                    Registros.setFechaingreso(cursor.getString(8));
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
    public registros eliminar_daCompleto(Context context, int identificador) {
        int i = identificador;
        registros elida = new registros();
        Conexi conexi = new Conexi(context);
        SQLiteDatabase database = conexi.getWritableDatabase();

        database.delete("registro_pedidos" ,"id=" + i ,null);

        //DELETE FROM registro_pedidos WHERE id=" + i + "
        return elida;
    }

}


