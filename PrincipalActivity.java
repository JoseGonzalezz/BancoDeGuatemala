package com.example.bancogt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void Cerrar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Cuentas(View view){
        Intent i = new Intent(this, CuentasActivity.class);
        startActivity(i);
    }

    public void TipoCambio(View view){
        Intent i = new Intent(this, TipoCambioActivity.class);
        startActivity(i);
    }

}