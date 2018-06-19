package com.example.gabrielascurra.productos.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.Modelo.Servicio;
import com.example.gabrielascurra.productos.R;

public class Anadir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        final Servicio servicio=new Servicio(this);
        final EditText editTextCodigo= findViewById(R.id.etNuevoCodigo);
        final EditText editTextNombre=findViewById(R.id.etNuevoNombre);
        final EditText editTextPrecio=findViewById(R.id.etNuevoPrecio);
        Button anadir=findViewById(R.id.btnNuevoAnadir);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo= Integer.parseInt(editTextCodigo.getText().toString());
                String nombre=editTextNombre.getText().toString();
                float precio;

                if(editTextPrecio.getText().toString().equals("")){
                    precio=0;
                }else {
                    precio=Float.parseFloat(editTextPrecio.getText().toString());
                }
                long operacion=servicio.nuevoProducto(new Producto(codigo,nombre,precio));
                if (operacion!=-1){
                    Toast.makeText(Anadir.this, "Producto añadido exitosamente", Toast.LENGTH_SHORT).show();
                   onBackPressed();
                }else {
                    Toast.makeText(Anadir.this, "Error al anadir producto,el codigo ya existe", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }
}
