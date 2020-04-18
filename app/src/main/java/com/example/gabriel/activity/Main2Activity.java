package com.example.gabriel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriel.R;
import com.example.gabriel.dao.DaoRegistros;
import com.example.gabriel.modelo.registros;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    EditText nombre, direccion, descripcion, valor,fecha;
    Button registro, consulta, modificar, eliminar;
    String bandera=null;
    int identificador;
    private int año,mes,dia;
    private EditText campofecha;
    private Button botnfecha;
    private static final int tipo_dialogo = 0;
    private static DatePickerDialog.OnDateSetListener oyentedeselectorfecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nombre=findViewById(R.id.Edt_Nombre);
        direccion=findViewById(R.id.Edt_Direccion);
        descripcion=findViewById(R.id.Edt_Descripcion);
        valor=findViewById(R.id.Edt_Valor);
        fecha=findViewById(R.id.Edt_Fecha);
        registro=findViewById(R.id.Btn_Registrar);
        consulta=findViewById(R.id.Btn_Consultar);
        modificar=findViewById(R.id.Btn_Modificar);
        eliminar=findViewById(R.id.Btn_Eliminar);
        //lkll
        campofecha=findViewById(R.id.Edt_Fecha);
        botnfecha=findViewById(R.id.Btn_fecha);
        Calendar calendario = Calendar.getInstance();
        año=calendario.get(Calendar.YEAR);
        mes=calendario.get(Calendar.MONTH);
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();
        oyentedeselectorfecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                año=year;
                mes=monthOfYear;
                dia=dayOfMonth;
                mostrarfecha();
            }
        };
        ///

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            identificador=bundle.getInt("id");
            this.viajero(identificador);
            //Toast toast12= Toast.makeText(this,"Viene de consultar listo para eliminar  "+bandera,Toast.LENGTH_SHORT);
            //toast12.show();
            modificar.setVisibility(View.VISIBLE);
            eliminar.setVisibility(View.VISIBLE);
            registro.setEnabled(false);
        }else {
           identificador=0;
            //Toast toast34= Toast.makeText(this,"aun no "+ bandera,Toast.LENGTH_SHORT);
            //toast34.show();
            modificar.setVisibility(View.INVISIBLE);
            eliminar.setVisibility(View.INVISIBLE);
        }

          }

    public void viajero(int identificador) {
        DaoRegistros daoRegistros = new DaoRegistros();
        registros registros = daoRegistros.read_one(this,identificador);
        nombre.setText(registros.getNombre());
        descripcion.setText(registros.getDescripcion());
        direccion.setText(registros.getDireccion());
        valor.setText(registros.getValor());
        fecha.setText(registros.getFecha());
    }

    public void Onclick(View view) {

        switch (view.getId()){
            case R.id.Btn_Registrar:
                registrar();
                limpiar();
                break;
            case R.id.Btn_Consultar:
                consulta();
                break;

            case  R.id.Btn_Eliminar:
                mens();
                break;

            case R.id.Btn_Modificar:
            Vistamodificar(identificador);
                break;

        }
    }
    private void registrar() {
        registros object = new registros();
        DaoRegistros dao = new DaoRegistros();
        object.setNombre(nombre.getText().toString());
        object.setDireccion(direccion.getText().toString());
        object.setDescripcion(descripcion.getText().toString());
        object.setValor(valor.getText().toString());
        object.setFecha(fecha.getText().toString());
        if(dao.create(object,this)==true){
            Toast toast = Toast.makeText(getApplicationContext(),"Registro Guardado",Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast1 = Toast.makeText(getApplicationContext(),"Problem..",Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    private void Vistamodificar(int via) {
        registros object = new registros();
        DaoRegistros dao = new DaoRegistros();
       // registros regis = daoRegistros.modificar_da(this,via);

        object.setNombre(nombre.getText().toString());
        object.setDireccion(direccion.getText().toString());
        object.setDescripcion(descripcion.getText().toString());
        object.setValor(valor.getText().toString());
        object.setFecha(fecha.getText().toString());

        if(dao.modificar_da(object,this,via)==true){
            Toast toast = Toast.makeText(getApplicationContext(),"Registro Modificado",Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast1 = Toast.makeText(getApplicationContext(),"One Problem",Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    private void eliminardatos(int identificador) {
        DaoRegistros daoRegistros2 = new DaoRegistros();
        registros registros2 = daoRegistros2.eliminar_da(this,identificador);

        nombre.setText(registros2.getNombre());
        descripcion.setText(registros2.getDescripcion());
        direccion.setText(registros2.getDireccion());
        valor.setText(registros2.getValor());
        fecha.setText(registros2.getFecha());
    }
    public void mens (){
        final CharSequence [] opcion = {"si","no"};
        final AlertDialog.Builder alerta = new AlertDialog.Builder(Main2Activity.this);
        alerta.setTitle("Estas seguro de eliminar el registro");
        alerta.setItems(opcion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opcion[i].equals("si")){
                    eliminardatos(identificador);
                    eliminar.setEnabled(false);
                    modificar.setEnabled(false);
                    dialogInterface.dismiss();
                }else if(opcion[i].equals("no")){
                    //
                }
            }
        });
        alerta.show();
    }




    private void consulta() {
        Intent intent3 = new Intent(this,Main3Activity.class);
        this.bandera="consul";
        intent3.putExtra("consu",bandera);
        startActivity(intent3);


    }



    public void limpiar(){
        this.nombre.setText("");
        this.direccion.setText("");
        this.descripcion.setText("");
        this.valor.setText("");
        this.fecha.setText("");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return  new DatePickerDialog(this,oyentedeselectorfecha,año,mes,dia);

        }
        return null;
    }

    public  void  mostrarCalendario(View control){
        showDialog(tipo_dialogo);
    }
    public void mostrarfecha(){
        campofecha.setText(año+"/"+(mes+1)+"/"+dia);
    }

    ////,lglñgxñl

    }

