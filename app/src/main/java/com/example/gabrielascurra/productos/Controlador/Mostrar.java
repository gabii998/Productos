package com.example.gabrielascurra.productos.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.Modelo.Servicio;
import com.example.gabrielascurra.productos.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    ListView lista;
    TextView listaVacia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        listaVacia=findViewById(R.id.listaVacia);
        listaVacia.setVisibility(View.INVISIBLE);
        lista=findViewById(R.id.lvProductos);
        poblarLista(lista,listaVacia);
    }
    public void poblarLista(ListView lista, TextView textView){
        Servicio servicio=new Servicio(this);
        ArrayList<Producto> productos=servicio.MostrarProductos();
        if (! productos.isEmpty()){
            textView.setVisibility(View.INVISIBLE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        final ProductoAdapter adaptador=new ProductoAdapter(this,productos);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(),Modificar.class);
                Producto p=adaptador.getItem(position);
                intent.putExtra("Codigo",p.getCodigoProducto());
                intent.putExtra("Nombre",p.getNombre());
                intent.putExtra("Precio",p.getPrecio());
                startActivity(intent);
                adaptador.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        poblarLista(lista,listaVacia);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.get_item:
                Intent intent=new Intent(getApplicationContext(),Anadir.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
