package com.example.gabrielascurra.productos.Modelo;

public class Producto {
    private int codigoProducto;
    private String nombre;
    private float precio;

    public Producto(int codigoProducto, String nombre, float precio) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
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
}
