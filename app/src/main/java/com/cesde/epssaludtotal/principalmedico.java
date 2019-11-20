package com.cesde.epssaludtotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class principalmedico extends AppCompatActivity {

    ImageButton salirmedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalmedico);

        salirmedico = findViewById(R.id.imageBtn_medico_salir);


        salirmedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salir = new Intent(principalmedico.this, MainActivity.class);
                startActivity(salir);

            }
        });


    }
}
