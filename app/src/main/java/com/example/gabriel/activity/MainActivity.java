package com.example.gabriel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriel.R;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    Button ingreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=findViewById(R.id.Txt_Usuario);
        contraseña=findViewById(R.id.Txt_Contraseña);
        ingreso=findViewById(R.id.Btn_Ingreso);

    }

    private void consulta() {
        String usu = "gabo", contra="gabo";
        String usuar = usuario.getText().toString();
        String contras= contraseña.getText().toString();
        if(usuar.equals(usu) && contras.equals(contra)){
            inicio();
        }else{
            Toast toast= Toast.makeText(this,"COntraseña INvalida",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onuno(View view) {

        switch (view.getId()){
            case R.id.Btn_Ingreso:
                consulta();
                break;
        }
    }

    private void inicio() {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
            finish();
    }
}
