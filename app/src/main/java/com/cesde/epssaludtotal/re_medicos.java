package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class re_medicos extends AppCompatActivity {

    EditText mcedula, mnombre, mcorreo,mespecialidad,mclave;
    ImageButton guardarmedico;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_medicos);

        // PARA ESTABLECER LA <- DE DEVOLVER
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mcedula = findViewById(R.id.ed_medi_cedula);
        mnombre = findViewById(R.id.ed_medi_nombre);
        mcorreo = findViewById(R.id.ed_medi_correo);
        mespecialidad = findViewById(R.id.ed_medi_especialidad);
        mclave = findViewById(R.id.ed_medi_clave);

        guardarmedico = findViewById(R.id.imabtnguardarme);

        guardarmedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Medico")//nombre de la coleccion en la base de datos firestor
                        .whereEqualTo("cedula",mcedula.getText().toString())
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
                                    Guardarmedico();
                                }else{
                                    Toast.makeText(getApplicationContext(),"ya existe",Toast.LENGTH_LONG).show();
                                }


                            }
                        });



            }

        });

    }

    public void Guardarmedico(){

        if(!mcedula.getText().toString().isEmpty() && !mnombre.getText().toString().isEmpty()){
            Map<String,Object>Medico = new HashMap<>(); //crea la colecion
            Medico.put("cedula" , mcedula.getText().toString());
            Medico.put("nombre" , mnombre.getText().toString());
            Medico.put("correo" , mcorreo.getText().toString());
            Medico.put("especialidad" , mespecialidad.getText().toString());
            Medico.put("clave" , mclave.getText().toString());

            db.collection("Medico").add(Medico).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_LONG).show();
                    mcedula.setText("");
                    mnombre.setText("");
                    mcorreo.setText("");
                    mespecialidad.setText("");
                    mclave.setText("");




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
