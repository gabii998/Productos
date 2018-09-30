package com.example.gabrielascurra.productos.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLQuery extends SQLiteOpenHelper{
    private String nombre;

    public SQLQuery(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        nombre=name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nombre+"(prod_nombre text NOT NULL,prod_cantidad integer,prod_precio real)");
        db.execSQL("create table Historial(hist_fecha text NOT NULL,hist_cantidad integer,hist_productos text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
