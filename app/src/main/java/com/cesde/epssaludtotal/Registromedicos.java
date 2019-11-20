package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Registromedicos extends AppCompatActivity {

     Button registromedicos;

     ListView listarmedicos;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //conexion bd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registromedicos);

        listarmedicos = findViewById(R.id.lv_listarmedicos);

        // PARA ESTABLECER LA <- DE DEVOLVER
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db.collection("Medicos")
                .whereEqualTo("nombre", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });



        registromedicos = findViewById(R.id.btn_re_medicos);
        registromedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevomedico = new Intent(Registromedicos.this, re_medicos.class);
                startActivity(nuevomedico);
            }
        });

    }

}


