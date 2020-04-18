package com.example.gabriel.interfaces;

import android.content.Context;

import com.example.gabriel.activity.Main2Activity;
import com.example.gabriel.activity.Main3Activity;
import com.example.gabriel.modelo.registros;

import java.util.ArrayList;

public interface Iregistros {

    public boolean create (registros object ,Main2Activity context);

    public ArrayList<registros> read(Main3Activity context);

    //metodo de sustraccion y carga
    registros read_one(Context context, int id);

    registros eliminar_da(Context context, int identificador);

   public boolean  modificar_da(registros object ,Main2Activity contexts, int via);
}
