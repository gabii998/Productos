package com.example.gabrielascurra.productos.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gabrielascurra.productos.modelo.Historico;
import com.example.gabrielascurra.productos.modelo.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoricoDAO {
    private Context contexto;

    public HistoricoDAO(Context contexto) {
        this.contexto = contexto;
    }

    public long nuevoHistorico(Historico historico)  {
        SQLQuery query=new SQLQuery(contexto,"Productos",null,1);
        SQLiteDatabase bd=query.getWritableDatabase();
        ContentValues valores=new ContentValues();
        try {
            valores.put("hist_productos",encodeProductos(historico));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valores.put("hist_fecha",historico.getDia());
        valores.put("hist_cantidad",historico.getCantidadElementos());
        long insercion=bd.insert("Historial", null, valores);
        bd.close();
        return insercion;
    }
    public ArrayList<Historico> obtenerHistorial(){
        ArrayList<Historico> historico=new ArrayList<>();
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        Cursor cursor = bd.rawQuery("Select hist_fecha,hist_productos,hist_cantidad from Historial",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                try {
                    historico.add(new Historico(cursor.getString(cursor.getColumnIndex("hist_fecha")),
                            decodeProductos(cursor.getString(cursor.getColumnIndex("hist_productos"))),
                            cursor.getInt(cursor.getColumnIndex("hist_cantidad"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return historico;
    }
    public String encodeProductos(Historico historico) throws JSONException {
        JSONArray jsonArray= new JSONArray();
        ArrayList<Producto> productos=historico.getProductos();
        for (int i=0;i<productos.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("nombre",productos.get(i).getNombre());
            jsonObject.put("cantidad",productos.get(i).getCantidad());
            jsonObject.put("precio",productos.get(i).getPrecio());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
    public ArrayList<Producto> decodeProductos(String encodedJson) throws JSONException {
        JSONArray jsonArray=new JSONArray(encodedJson);
        ArrayList<Producto> productos=new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            Producto producto= new Producto();
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            producto.setNombre(jsonObject.getString("nombre"));
            producto.setCantidad(jsonObject.getInt("cantidad"));
            producto.setPrecio((float)jsonObject.getDouble("precio"));
            productos.add(producto);
        }
        return productos;
    }
    public void vaciarTabla(){
        SQLQuery query = new SQLQuery(contexto, "Productos", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        bd.execSQL("DELETE FROM Historial");
        bd.close();
    }
}
