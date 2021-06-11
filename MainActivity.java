package com.example.bancogt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText adtUsuario, adtPass;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adtUsuario = findViewById(R.id.edtUsuario);
        adtPass = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.Ingresarbtn);

    }

    public void Ingresar(){
        Intent i = new Intent(this, PrincipalActivity.class);
        startActivity(i);
    }

    public void Validar(View view){

        String usuario1, usuario2, usuario3, contrasena, leeusuario, leecontrasena;
        contrasena = "1234";
        usuario1 = "Jorge";
        usuario2 = "Jose";
        usuario3 = "Juan";

        leeusuario = adtUsuario.getText().toString();
        leecontrasena = adtPass.getText().toString();

        if(leeusuario.isEmpty() || leecontrasena.isEmpty()){
            Toast.makeText(MainActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(usuario1.equals(leeusuario) || usuario2.equals(leeusuario) || usuario3.equals(leeusuario)){
                if(contrasena.equals(leecontrasena)){
                    Ingresar();
                }else{
                    Toast.makeText(MainActivity.this, "Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(MainActivity.this, "Usuario incorrecto",Toast.LENGTH_SHORT).show();
             }
        }
    }


}