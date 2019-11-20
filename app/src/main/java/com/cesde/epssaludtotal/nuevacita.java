package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cesde.epssaludtotal.Modelos.Medicos;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class nuevacita extends AppCompatActivity {

    EditText fecha, hora, motivo,nombremedicocita;
    TextView Listarnombremedico;

    ImageButton guardarcitanueva ,salircita;
    ;

    public Medicos medicos;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //conexion bd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevacita);

        salircita = findViewById(R.id.imageBtn_paciente_salir);


        fecha=findViewById(R.id.ed_fecha);
        hora=findViewById(R.id.ed_hora);
        motivo=findViewById(R.id.ed_motivocita);
        nombremedicocita=findViewById(R.id.ed_motivocita);
        guardarcitanueva=findViewById(R.id.imabtnguardarcitanueva);

        /*DocumentReference docRef = db.collection("Medico").document("id");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Medicos medicos = documentSnapshot.toObject(Medicos.class);

               // return medicos.setNombre();

            }

        });*/

       // Listarnombremedico.setText(""+medicos);


        salircita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salir = new Intent(nuevacita.this, MainActivity.class);
                startActivity(salir);

            }
        });

        guardarcitanueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Cita")//nombre de la coleccion en la base de datos firestor
                        .whereEqualTo("fecha",fecha.getText().toString())
                        .whereEqualTo("hora",hora.getText().toString())
                        //.whereEqualTo("clave",pdocumento.getText().toString())
                        .get()
                        .addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"fallo",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(@NonNull  QuerySnapshot queryDocumentSnapshots) {
                                boolean existe = false;
                                if(queryDocumentSnapshots.getDocuments() != null){
                                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                                        //alert(document.getId()+"=>"+document.getData());
                                        existe = true;
                                        //Toast.makeText(getApplicationContext(),"ya existe",Toast.LENGTH_LONG).show();
                                    }

                                }
                                if(!existe){
                                    guardacita();
                                }else{
                                    Toast.makeText(getApplicationContext(),"ya exita con esa  misma fecha y hora",Toast.LENGTH_LONG).show();
                                }


                            }
                        });



            }

        });




    }
    public void guardacita(){
        if(!fecha.getText().toString().isEmpty() && !hora.getText().toString().isEmpty() && !nombremedicocita.getText().toString().isEmpty()){
            Map<String,Object> Citas = new HashMap<>(); //crea la colecion
            Citas.put("fecha" , fecha.getText().toString());
            Citas.put("hora" , hora.getText().toString());
            Citas.put("motivo" , motivo.getText().toString());
            Citas.put("nombre medico" , nombremedicocita.getText().toString());



            db.collection("Citas").add(Citas).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference)
                {
                    fecha.setText("");
                    hora.setText("");
                    motivo.setText("");
                    nombremedicocita.setText("");

                    Toast.makeText(getApplicationContext(),"Cita guardada con éxito, recuerda llevar tu documento",Toast.LENGTH_LONG).show();



                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(),""+ e ,Toast.LENGTH_LONG).show();

                        }
                    });
        }
        else{
            Toast.makeText(getApplicationContext(),"Ingrese todos los datos",Toast.LENGTH_LONG).show();

        }
    }
}
