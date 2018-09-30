package com.example.gabrielascurra.productos.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoricoSQLQuery extends SQLiteOpenHelper {
    String nombre;

    public HistoricoSQLQuery( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.nombre = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nombre+"(hist_fecha text NOT NULL,hist_cantidad integer,hist_productos text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
