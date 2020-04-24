package com.example.gabriel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriel.R;
import com.example.gabriel.dao.DaoRegistros;
import com.example.gabriel.modelo.registros;

public class Main4Activity extends AppCompatActivity {
    ListView lista_re_eli;
    int i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Context context;
        lista_re_eli = findViewById(R.id.Lst_Registros_Eliminados);
        cargalista2();

        ////pilas
    }

    public void cargalista2() {
        DaoRegistros daoRegistros = new DaoRegistros();
        final ArrayAdapter<registros> adapter = new ArrayAdapter<registros>(Main4Activity.this, android.R.layout.simple_list_item_1, daoRegistros.read_eli(this));
        lista_re_eli.setAdapter(adapter);

        lista_re_eli.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean res = false;

                final registros regis = adapter.getItem(i);
               i3=regis.getId_registros();


                final CharSequence[] opcion = {"Activar", "Eliminar"};
                final AlertDialog.Builder alerta = new AlertDialog.Builder(Main4Activity.this);
                alerta.setTitle("Puedes Cambiar el Estado Activo o Eliminar por completo ");
                alerta.setItems(opcion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i2) {

                        if (opcion[i2].equals("Activar")) {
                          DaoRegistros daoRegistros1 = new DaoRegistros();
                          registros object = new registros();
                          object.setEstado(1);

                            if (daoRegistros1.cambiaEstado(object,Main4Activity.this,i3) == true) {

                                Toast toast = Toast.makeText(getApplicationContext(), "Registro Activado", Toast.LENGTH_SHORT);
                                toast.show();


                            } else {
                                Toast toast1 = Toast.makeText(getApplicationContext(), "One Problem1", Toast.LENGTH_SHORT);
                                toast1.show();

                            }
                            dialogInterface.dismiss();
                        } else if (opcion[i2].equals("Eliminar")) {

                            DaoRegistros da = new DaoRegistros();
                            registros ob = new registros();

                            if (da.cambiaEstado(ob,Main4Activity.this,i3) == true) {

                                Toast toast = Toast.makeText(getApplicationContext(), "Registro Eliminado por completo", Toast.LENGTH_SHORT);
                                toast.show();


                            } else {
                                Toast toas1 = Toast.makeText(getApplicationContext(), "One Problem1", Toast.LENGTH_SHORT);
                                toas1.show();

                            }
                            dialogInterface.dismiss();

                        }else {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "One Problem1", Toast.LENGTH_SHORT);
                            toast1.show();

                        }
                        dialogInterface.dismiss();
                    }

                });
                alerta.show();
                return true;
            }
        });

    }
}
