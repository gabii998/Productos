package com.example.gabrielascurra.productos.Controlador;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrielascurra.productos.AdapterCallback;
import com.example.gabrielascurra.productos.R;
import com.example.gabrielascurra.productos.databinding.ItemListaBinding;
import com.example.gabrielascurra.productos.modelo.Producto;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Producto> productos;
    private AdapterCallback clickListener;

    public RecyclerViewAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListaBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_lista,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.bindConnection(productos.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickListener.onLongItemClick(v,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    public void setOnItemClickListener(AdapterCallback clickListener){
        this.clickListener=clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemListaBinding binding;

        public MyViewHolder(ItemListaBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bindConnection(Producto producto){
            binding.setProducto(producto);
        }
    }

}
