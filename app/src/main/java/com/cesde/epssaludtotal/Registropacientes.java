package com.cesde.epssaludtotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registropacientes extends AppCompatActivity {

    Button registropaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registropacientes);

        // PARA ESTABLECER LA <- DE DEVOLVER
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        registropaciente = findViewById(R.id.btn_re_pacientes);

        registropaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevopaciente = new Intent(Registropacientes.this, re_pacientes.class);
                startActivity(nuevopaciente);
            }
        });

    }
}
