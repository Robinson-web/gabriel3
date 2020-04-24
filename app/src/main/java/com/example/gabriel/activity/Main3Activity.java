package com.example.gabriel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriel.activity.Main2Activity;

import com.example.gabriel.R;
import com.example.gabriel.conexion.Conexi;
import com.example.gabriel.dao.DaoRegistros;
import com.example.gabriel.modelo.registros;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ListView lista_re;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.lista_re = findViewById(R.id.Lst_Registros);
        cargalista();

    }

    public void cargalista() {
        DaoRegistros daoRegistros = new DaoRegistros();
        final ArrayAdapter<registros> adapter = new ArrayAdapter<registros>(Main3Activity.this, android.R.layout.simple_list_item_1, daoRegistros.read(this));
        lista_re.setAdapter(adapter);

        /*lista_re.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registros regis = adapter.getItem(i);

                Intent intent2 = new Intent(getApplicationContext(), Main2Activity.class);
                intent2.putExtra("id", regis.getId_registros());
                startActivity(intent2);

            }
        });*/
        lista_re.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                registros regis = adapter.getItem(i);

                Intent intent2 = new Intent(getApplicationContext(), Main2Activity.class);
                intent2.putExtra("id", regis.getId_registros());
                startActivity(intent2);
                return true;
            }
        });
    }


}
