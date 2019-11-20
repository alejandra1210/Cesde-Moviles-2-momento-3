package com.cesde.epssaludtotal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class principalpaciente extends AppCompatActivity {

    Button irnuevacita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalpaciente);



        irnuevacita = findViewById(R.id.btn_nuevacitap);

         irnuevacita.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent ircita = new Intent(principalpaciente.this, nuevacita.class);
                 startActivity(ircita);
             }
         });

    }
}
