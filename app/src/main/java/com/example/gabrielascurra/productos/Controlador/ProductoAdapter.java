package com.example.gabrielascurra.productos.Controlador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gabrielascurra.productos.R;
import com.example.gabrielascurra.productos.modelo.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    /*En esta clase,se crea un adaptador para cada item de la lista,
    * utilizando el layout item_lista.xml*/

    private Activity activity;
    private ArrayList<Producto> productos;

    public ProductoAdapter(Activity activity, ArrayList<Producto> productos) {
        this.activity = activity;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Producto getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista=convertView;
        if (convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista=inf.inflate(R.layout.item_lista,null);
        }
        Producto prod=productos.get(position);//Se toma el indice del item seleccionado en la lista
        //Se asignan los elementos del item_lista.xml
        TextView codigo=vista.findViewById(R.id.txtCodigo);
        TextView nombre=vista.findViewById(R.id.txtNombre);
        TextView precio=vista.findViewById(R.id.txtPrecio);
        //Se le setea cada atributo del objeto recuperado
        codigo.setText("Cantidad: "+String.valueOf(prod.getCantidad()));
        nombre.setText("Nombre: "+prod.getNombre());
        precio.setText("Precio: $"+String.valueOf(prod.getPrecio()) );
        return vista;
    }
}
