package com.example.bancogt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginCuentaActivity extends AppCompatActivity {

    EditText adtPass;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cuenta);

        adtPass = findViewById(R.id.edtPassword2);
        btnIngresar = findViewById(R.id.Ingresarbtn);

    }
    public void Ingresar(){
        Intent i = new Intent(this, PrincipalCuentaActivity.class);
        startActivity(i);
    }

    public void Validar(View view){

        String contrasena, leecontrasena;
        contrasena = "1234";


        leecontrasena = adtPass.getText().toString();

        if(leecontrasena.isEmpty()){
            Toast.makeText(LoginCuentaActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(contrasena.equals(leecontrasena)) {
                Ingresar();
            }else{
                    Toast.makeText(LoginCuentaActivity.this, "Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

