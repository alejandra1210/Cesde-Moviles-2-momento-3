package com.cesde.epssaludtotal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cesde.epssaludtotal.Modelos.MAdm;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class loginadministrador extends AppCompatActivity {

    ImageButton entraradm;
    EditText adm_nomb, adm_clave;
    private MAdm administrador;
   private SharedPreferences preferences;



    FirebaseFirestore db = FirebaseFirestore.getInstance(); //conexion bd


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadministrador);

        // PARA ESTABLECER LA <- DE DEVOLVER
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        entraradm = findViewById(R.id.btn_entraradm);
        adm_nomb = findViewById(R.id.ed_login_nombadm);
        adm_clave = findViewById(R.id.ed_login_claveadm);
        //Toast.makeText(getApplicationContext(),"dkfkd",Toast.LENGTH_LONG).show();

      //  preferences = this.getSharedpreferences("session", Context.MODE_PRIVATE);

        administrador = new MAdm();

        entraradm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //adm_nomb.getText().toString();
                //adm_clave.getText().toString();
                //Toast.makeText(getApplicationContext(),adm_nomb.getText().toString()+" "+adm_clave.getText().toString(),Toast.LENGTH_LONG).show();



                db.collection("Administrador")//nombre de la coleccion en la base de datos firestor
                        .whereEqualTo("usuario",adm_nomb.getText().toString())
                        .whereEqualTo("clave",adm_clave.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    if(task.getResult() != null && !task.getResult().getDocuments().isEmpty()){

                                        Toast.makeText(getApplicationContext(),"Ingreso exitoso",Toast.LENGTH_LONG).show();



                                        irprincipal();

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
                                        Toast.makeText(getApplicationContext(),"Ingreso exitoso",Toast.LENGTH_LONG).show();



                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(),"Sin datos",Toast.LENGTH_LONG).show();
                                }

                            }
                        });


















            }
        });

    }

    public void  irprincipal(){  // ir a principal de administrador
        Intent iradm = new Intent(loginadministrador.this, Administrador.class);
        iradm.putExtra("nombre",adm_nomb.getText().toString());
        startActivity(iradm);

    }

   public void alert(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();

    }
    private void guardarreferencias(MAdm administrador){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Inicio",true);
        editor.putString("usuario",administrador.getUsuario());
        editor.putString("clave",administrador.getUsuario());
        editor.commit();
        //irprincipal();



    }

}
