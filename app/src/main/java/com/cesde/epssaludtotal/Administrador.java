package com.cesde.epssaludtotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Administrador extends AppCompatActivity {

    Button medicos, pacientes;
    ImageButton salir;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        medicos = findViewById(R.id.btn_medicos);
        pacientes  = findViewById(R.id.btn_pacientes);
        salir = findViewById(R.id.imageBtn_adm_salir);
        nombre = findViewById(R.id.tv_nom_roladm);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("nombre")!= null)
        {
            nombre.setText(bundle.getString("nombre"));
        }
        medicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irmedicos = new Intent(Administrador.this, Registromedicos.class);
                startActivity(irmedicos);
            }
        });

        pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irpacientes = new Intent(Administrador.this, Registropacientes.class);
                startActivity(irpacientes);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salir = new Intent(Administrador.this, MainActivity.class);
                startActivity(salir);

            }
        });

    }
}
