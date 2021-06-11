package com.example.bancogt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_cuenta);
    }
    public void Cerrar(View view){
        Intent i = new Intent(this, PrincipalActivity.class);
        startActivity(i);
    }
}