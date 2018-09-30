package com.example.gabrielascurra.productos.modelo;

import java.util.ArrayList;

public class Historico {
    private String dia;
    private ArrayList<Producto> productos;
    private int cantidadElementos;

    public Historico(String dia, int cantidadElementos) {
        this.dia = dia;
        this.cantidadElementos = cantidadElementos;
    }

    public Historico(String dia, ArrayList<Producto> productos, int cantidadElementos) {
        this.dia = dia;
        this.productos = productos;
        this.cantidadElementos = cantidadElementos;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public void setCantidadElementos(int cantidadElementos) {
        this.cantidadElementos = cantidadElementos;
    }
}
