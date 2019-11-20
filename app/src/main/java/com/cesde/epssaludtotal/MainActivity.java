package com.cesde.epssaludtotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton administrador, pacientes, medicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        administrador = findViewById(R.id.btn_ir_adm);
        pacientes = findViewById(R.id.btn_ir_usuario);
        medicos = findViewById(R.id.btn_ir_medico);

        administrador.setOnClickListener(new View.OnClickListener() { //boton para login administracion
            @Override
            public void onClick(View view) {
                Intent irloginadmi = new Intent(MainActivity.this, loginadministrador.class);
                startActivity(irloginadmi);

            }
        });


        pacientes.setOnClickListener(new View.OnClickListener() { //boton para login usuario
            @Override
            public void onClick(View view) {
                Intent irloginusuario = new Intent(MainActivity.this, loginusuarios.class);
                startActivity(irloginusuario);

            }
        });

        medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irloginmedico = new Intent(MainActivity.this, loginmedico.class);
                startActivity(irloginmedico);

            }
        });

    }
}
