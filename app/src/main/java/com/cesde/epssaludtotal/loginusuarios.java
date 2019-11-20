package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class loginusuarios extends AppCompatActivity {

    ImageButton entrarusuarios;
    EditText logintd, loginclave;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //conexion bd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginusuarios);

        entrarusuarios = findViewById(R.id.imabtnentrarusuarios);
        logintd = findViewById(R.id.ed_login_nombusuarios);
        loginclave = findViewById(R.id.ed_login_claveusuario);



        entrarusuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //adm_nomb.getText().toString();
                //adm_clave.getText().toString();
                //Toast.makeText(getApplicationContext(),adm_nomb.getText().toString()+" "+adm_clave.getText().toString(),Toast.LENGTH_LONG).show();



                db.collection("Paciente")//nombre de la coleccion en la base de datos firestor
                        .whereEqualTo("documento",logintd.getText().toString())
                        .whereEqualTo("clave",loginclave.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    if(task.getResult() != null && !task.getResult().getDocuments().isEmpty()){

                                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();



                                        irusuarios();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Usuario o clave incorrecta",Toast.LENGTH_LONG).show();
                                    }


                                }else{
                                    Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_LONG).show();
                                }
                            }
                        })
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
                                if(queryDocumentSnapshots.getDocuments() != null){
                                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                                        //alert(document.getId()+"=>"+document.getData());
                                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();



                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(),"Sin datos",Toast.LENGTH_LONG).show();
                                }

                            }
                        });


















            }
        });

    }
    public void  irusuarios(){ //metodo irusuario, lo mismo que intent
        Intent irusuarios = new Intent(loginusuarios.this, principalpaciente.class); //donde vamos
        startActivity(irusuarios);

    }

}
