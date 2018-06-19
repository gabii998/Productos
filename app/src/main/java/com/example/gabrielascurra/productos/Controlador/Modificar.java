package com.example.gabrielascurra.productos.Controlador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.Modelo.Servicio;
import com.example.gabrielascurra.productos.R;

public class Modificar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        //Se asignan los elementos de la vista
        final EditText etCodigo=findViewById(R.id.etModCodigo);
        final EditText etNombre=findViewById(R.id.etModNombre);
        final EditText etPrecio=findViewById(R.id.etModPrecio);
        Button btEliminar=findViewById(R.id.btnModEliminar);
        Button btGuardar=findViewById(R.id.btnModGuardar);
        etCodigo.setEnabled(false);
        //Se toman los mensajes enviados en el intent
        final int codigo=getIntent().getIntExtra("Codigo",0);
        String nombre=getIntent().getStringExtra("Nombre");
        float precio=getIntent().getFloatExtra("Precio",0);
        //Se crea un onclicklistener en comun para los dos botones
        View.OnClickListener buttonListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Servicio servicio= new Servicio(getApplicationContext());
                switch (v.getId()){
                    case R.id.btnModEliminar:
                        int eliminar=servicio.eliminarProducto(String.valueOf(codigo));
                        if(eliminar != -1){
                            Toast.makeText(Modificar.this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        break;
                    case R.id.btnModGuardar:
                        int cod=Integer.parseInt(etCodigo.getText().toString());
                        String nomb=etNombre.getText().toString();
                        float prec;
                        if(etPrecio.getText().toString().equals("")){//Comprueba que si es nulo,se le asigne 0 para evitar errores
                            prec=0;
                        }else {
                            prec=Float.parseFloat(etPrecio.getText().toString());
                        }

                        int modificar=servicio.modificarProducto(new Producto(cod,nomb,prec));
                        if (modificar != -1){
                            Toast.makeText(Modificar.this, "Producto modificado exitosamente", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }else{
                            Toast.makeText(Modificar.this, "No se guardaron los cambios", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        };
        //Se le asigna el onclicklistener creado arriba a los dos botones
        btEliminar.setOnClickListener(buttonListener);
        btGuardar.setOnClickListener(buttonListener);
        //Se asignan los datos obtenidos desde el activity anterior a los editTexts
        etCodigo.setText(String.valueOf(codigo));
        etNombre.setText(nombre);
        etPrecio.setText(String.valueOf(precio));
    }
}
