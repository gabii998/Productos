package com.example.gabrielascurra.productos.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gabrielascurra.productos.modelo.Producto;

import java.util.ArrayList;

public class ProductoDAO {
    /*Esta es la clase encargada de realizar todas las operaciones en la base de datos*/
    private Context contexto;

    public ProductoDAO(Context contexto) {
        this.contexto = contexto;
    }
    public long nuevoProducto(Producto producto) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        //valores.put("prod_cod", producto.getCodigoProducto());
        valores.put("prod_cantidad", producto.getCantidad());
        valores.put("prod_nombre", producto.getNombre());
        valores.put("prod_precio", producto.getPrecio());
        long insercion=bd.insert("Productos", null, valores);
        bd.close();
        return  insercion;
    }

    public int eliminarProducto(int code) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        int eliminar=bd.delete("Productos", "rowid=" + code, null);
        bd.close();
        return eliminar;
    }

    public int modificarProducto(Producto producto) {
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        //valores.put("prod_cod", producto.getCodigoProducto());
        valores.put("prod_cantidad",producto.getCantidad());
        valores.put("prod_nombre", producto.getNombre());
        valores.put("prod_precio", producto.getPrecio());
        int modificar=bd.update("Productos", valores, "rowid=" + producto.getRowid(), null);
        return modificar;
    }

    public ArrayList<Producto> MostrarProductos() {
        ArrayList<Producto> productos=new ArrayList<>();
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        Cursor cursor = bd.rawQuery("Select rowid,prod_nombre,prod_cantidad,prod_precio from Productos",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                productos.add(new Producto(cursor.getInt(cursor.getColumnIndex("rowid")),
                        cursor.getString(cursor.getColumnIndex("prod_nombre")),
                        cursor.getInt(cursor.getColumnIndex("prod_cantidad")),
                        cursor.getFloat(cursor.getColumnIndex("prod_precio")),contexto));
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return productos;
    }
    public void vaciarTabla(){
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        bd.execSQL("DELETE FROM Productos");
        bd.close();
    }
}
