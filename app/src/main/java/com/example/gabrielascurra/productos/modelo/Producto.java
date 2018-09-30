package com.example.gabrielascurra.productos.modelo;

import android.content.Context;

import com.example.gabrielascurra.productos.Datos.ProductoDAO;

public class Producto {
    private int rowid;
    private int cantidad;
    private String nombre;
    private float precio;
    private ProductoDAO dao;

    public Producto() {
    }

    public Producto(int rowid, String nombre, int cantidad, float precio, Context context) {
        this.cantidad=cantidad;
        this.rowid = rowid;
        this.nombre = nombre;
        this.precio = precio;
        dao=new ProductoDAO(context);
    }
    public Producto( String nombre, int cantidad ,float precio,Context context) {
        this.cantidad=cantidad;
        this.nombre = nombre;
        this.precio = precio;
        dao=new ProductoDAO(context);
    }
    public Producto(Context context){
        dao=new ProductoDAO(context);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ProductoDAO getDao() {
        return dao;
    }

    public void setDao(ProductoDAO dao) {
        this.dao = dao;
    }
}
