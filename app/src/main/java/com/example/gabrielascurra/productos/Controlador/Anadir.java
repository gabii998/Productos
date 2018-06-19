package com.example.gabrielascurra.productos.Controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.Datos.ProductoDAO;
import com.example.gabrielascurra.productos.R;

public class Anadir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        final ProductoDAO productoDAO =new ProductoDAO(this);//Creo un objeto de tipo productoDAO
        //Se asignan los elementos de la vista
        final EditText editTextCodigo= findViewById(R.id.etNuevoCodigo);
        final EditText editTextNombre=findViewById(R.id.etNuevoNombre);
        final EditText editTextPrecio=findViewById(R.id.etNuevoPrecio);
        Button anadir=findViewById(R.id.btnNuevoAnadir);
        //Se añade un onClickListener al unico botón de la vista
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo= Integer.parseInt(editTextCodigo.getText().toString());
                String nombre=editTextNombre.getText().toString();
                float precio;//No se asigna nada,porque en caso de que el campo estuviese vacio,generaba error

                if(editTextPrecio.getText().toString().equals("")){//Entonces si el campo está vacío,el valor es 0
                    precio=0;
                }else {
                    precio=Float.parseFloat(editTextPrecio.getText().toString());//Caso contrario,toma el valor del campo y lo parsea
                }
                //Es un long porque es el tipo de valor que retorna la operación,se utiliza para verificar que la operación haya sido exitosa
                long operacion= productoDAO.nuevoProducto(new Producto(codigo,nombre,precio));
                if (operacion!=-1){//Si no hay error
                    Toast.makeText(Anadir.this, "Producto añadido exitosamente", Toast.LENGTH_SHORT).show();//Lanza un mensaje por pantalla
                   onBackPressed();//Y vuelve a la pantalla principal
                }else {//Caso contrario
                    Toast.makeText(Anadir.this, "Error al anadir producto,el codigo ya existe", Toast.LENGTH_SHORT).show();
                    /*El error dice que el codigo ya existe,porque el unico caso encontrado en el que lanza
                    * error es cuando se ingresa un codigo ya existente(Eso debido a que el codigo es el primary key)*/
                }
                
            }
        });
    }
}
