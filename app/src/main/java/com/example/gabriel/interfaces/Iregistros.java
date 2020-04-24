package com.example.gabriel.interfaces;

import android.content.Context;

import com.example.gabriel.activity.Main2Activity;
import com.example.gabriel.activity.Main3Activity;
import com.example.gabriel.activity.Main4Activity;
import com.example.gabriel.modelo.registros;

import java.util.ArrayList;

public interface Iregistros {
//metodo de registar
    public boolean create (registros object ,Main2Activity context);
//metodo de lectura
    public ArrayList<registros> read(Main3Activity context);
//este consulta los registro eliminados o modificados a estado 0
    public ArrayList<registros> read_eli(Main4Activity context);

    //metodo de sustraccion y carga
    registros read_one(Context context, int id);
// metodo de eliminacion
    registros eliminar_da(Context context, int identificador);
//metodo para modificar
   public boolean  modificar_da(registros object ,Main2Activity context, int via);
   //interface para cambiar el estado
   public boolean cambiaEstado(registros object, Main4Activity context, int id);
}
