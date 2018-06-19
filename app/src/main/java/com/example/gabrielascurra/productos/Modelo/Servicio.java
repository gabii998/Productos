package com.example.gabrielascurra.productos.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class Servicio {
    Context contexto;

    public Servicio(Context contexto) {
        this.contexto = contexto;
    }

    public long nuevoProducto(Producto producto) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("prod_cod", producto.getCodigoProducto());
        valores.put("prod_nombre", producto.getNombre());
        valores.put("prod_precio", producto.getPrecio());
        long insercion=bd.insert("Productos", null, valores);
        bd.close();
        return  insercion;
    }

    public int eliminarProducto(String code) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        int eliminar=bd.delete("Productos", "prod_cod=" + code, null);
        bd.close();
        return eliminar;
    }

    public int modificarProducto(Producto producto) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("prod_cod", producto.getCodigoProducto());
        valores.put("prod_nombre", producto.getNombre());
        valores.put("prod_precio", producto.getPrecio());
        int modificar=bd.update("Productos", valores, "prod_cod=" + producto.getCodigoProducto(), null);
        return modificar;
    }

    public ArrayList<Producto> MostrarProductos() {
        ArrayList<Producto> productos=new ArrayList<>();
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        Cursor cursor = bd.rawQuery("Select prod_cod,prod_nombre,prod_precio from Productos",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                productos.add(new Producto(cursor.getInt(cursor.getColumnIndex("prod_cod")),
                        cursor.getString(cursor.getColumnIndex("prod_nombre")),
                        cursor.getFloat(cursor.getColumnIndex("prod_precio"))));
            } while (cursor.moveToNext());
        }
        bd.close();
        return productos;
    }
}
