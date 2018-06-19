package com.example.gabrielascurra.productos.Controlador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gabrielascurra.productos.Modelo.Producto;
import com.example.gabrielascurra.productos.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Producto> productos;

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
        Producto prod=productos.get(position);
        TextView codigo=vista.findViewById(R.id.txtCodigo);
        TextView nombre=vista.findViewById(R.id.txtNombre);
        TextView precio=vista.findViewById(R.id.txtPrecio);

        codigo.setText("Codigo: "+String.valueOf(prod.getCodigoProducto()));
        nombre.setText("Nombre: "+prod.getNombre());
        precio.setText("Precio: $"+String.valueOf(prod.getPrecio()) );
        return vista;
    }
}
