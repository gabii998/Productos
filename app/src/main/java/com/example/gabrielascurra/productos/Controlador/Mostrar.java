package com.example.gabrielascurra.productos.Controlador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.Modelo.Servicio;
import com.example.gabrielascurra.productos.R;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    private ListView lista;
    private TextView listaVacia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        //Se asignan los elementos de la vista
        listaVacia=findViewById(R.id.listaVacia);
        listaVacia.setVisibility(View.INVISIBLE);
        lista=findViewById(R.id.lvProductos);
        //Se invoca el metodo para poblar la lista
        poblarLista(lista,listaVacia);
    }
    public void poblarLista(ListView lista, TextView textView){
        /*La razon por la cual no se incluyo este codigo en el metodo onCreate,fue para
         * poderlo llamar cuando se actualizen los datos (en metodo onResume, que es cuando
         * la activity  vuelve a la vista del usuario) */
        Servicio servicio=new Servicio(this);
        ArrayList<Producto> productos=servicio.MostrarProductos();

        if (! productos.isEmpty()){
            textView.setVisibility(View.INVISIBLE);//Si hay producto en la bd,hace que el textView que indica lo contrario,no se muestre
        }else{
            textView.setVisibility(View.VISIBLE);//Si no hay productos cargados,muestra el textView
        }
        final ProductoAdapter adaptador=new ProductoAdapter(this,productos);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {/*Seteo de un listener para cuando se clickea
        en cada item de la lista*/
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(),Modificar.class);
                Producto p=adaptador.getItem(position);//Se crea un objeto a partir del item seleccionado
                //Y se envian los datos a la activity Modificar
                intent.putExtra("Codigo",p.getCodigoProducto());
                intent.putExtra("Nombre",p.getNombre());
                intent.putExtra("Precio",p.getPrecio());
                //Inicia la activity modificar
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        /*Este metodo se llama cada vez que la Activity vuelve a la vista del usuario*/
        poblarLista(lista,listaVacia);
        /*Y como cada vez que sucede eso, es porque se añadio,se borro,o se modifico la bd,
        * actualiza la lista*/
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Este metodo es para que se muestre el boton anadir en la toolbar
        getMenuInflater().inflate(R.menu.items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Y este metodo es para realizar acciones cuando se hace click en él
        switch (item.getItemId()){
            case R.id.get_item:
                Intent intent=new Intent(getApplicationContext(),Anadir.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
