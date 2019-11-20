package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cesde.epssaludtotal.Modelos.MAdm;
import com.cesde.epssaludtotal.Modelos.Medicos;
import com.cesde.epssaludtotal.Modelos.Pacientes;
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

public class re_pacientes extends AppCompatActivity {

    EditText pdocumento, pnombre, pcorreo,ptelefono,pclave;
    ImageButton guardarpaciente;
    private SharedPreferences preferences;
    private Pacientes pacientes;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pacientes);

        // PARA ESTABLECER LA <- DE DEVOLVER
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pdocumento = findViewById(R.id.ed_paci_documento);
        pnombre=findViewById(R.id.ed_paci_nombre);
        pcorreo = findViewById(R.id.ed_paci_correo);
        ptelefono = findViewById(R.id.ed_paci_telefono);
        pclave = findViewById(R.id.ed_paci_clave);

        guardarpaciente = findViewById(R.id.imabtnguardarpa);

        guardarpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("Paciente")//nombre de la coleccion en la base de datos firestor
                        .whereEqualTo("documento",pdocumento.getText().toString())
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
                                    Guardarpaciente();
                                }else{
                                    Toast.makeText(getApplicationContext(),"ya existe",Toast.LENGTH_LONG).show();
                                }


                            }
                        });










            }

        });


    }
    public void alert(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();

    }

    public void Guardarpaciente(){

        if(!pdocumento.getText().toString().isEmpty() && !pnombre.getText().toString().isEmpty()){
            Map<String,Object>Paciente = new HashMap<>(); //crea la colecion
            Paciente.put("documento" , pdocumento.getText().toString());
            Paciente.put("nombre" , pnombre.getText().toString());
            Paciente.put("correo" , pcorreo.getText().toString());
            Paciente.put("telefono" , ptelefono.getText().toString());
            Paciente.put("clave" , pclave.getText().toString());

            db.collection("Paciente").add(Paciente).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    Toast.makeText(getApplicationContext(),"Guardado con éxito",Toast.LENGTH_LONG).show();
                    pdocumento.setText("");
                    pnombre.setText("");
                    pcorreo.setText("");
                    ptelefono.setText("");
                    pclave.setText("");




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
