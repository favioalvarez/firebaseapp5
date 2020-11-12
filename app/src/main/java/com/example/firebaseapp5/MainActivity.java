package com.example.firebaseapp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Models.Profesionales;

public class MainActivity extends AppCompatActivity {
    private EditText enombre, esalario, eprofesion;
    private Button boton;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enombre = (EditText)findViewById(R.id.et_nombre);
        esalario =(EditText)findViewById(R.id.et_salario);
        eprofesion= (EditText)findViewById(R.id.et_profesion);
        boton=(Button)findViewById(R.id.btn);

        InicializarBase();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!enombre.getText().equals(""))
                {
                    Profesionales p = new Profesionales();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(enombre.getText().toString());
                    p.setSalario(esalario.getText().toString());
                    p.setProfesion(eprofesion.getText().toString());

                    databaseReference.child("profesionales").child(p.getId()).setValue(p);
                    Toast.makeText(getBaseContext(), " se ha guardado el profesional",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getBaseContext(), "no se ha guardado el profesional",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void InicializarBase()
    {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }
}