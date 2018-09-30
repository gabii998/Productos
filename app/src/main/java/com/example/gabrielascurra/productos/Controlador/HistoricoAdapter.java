package com.example.gabrielascurra.productos.Controlador;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.gabrielascurra.productos.R;
import com.example.gabrielascurra.productos.databinding.ItemListaHistorialBinding;
import com.example.gabrielascurra.productos.modelo.Historico;

import java.util.ArrayList;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder> {
    private ArrayList<Historico> historicos;

    public HistoricoAdapter(ArrayList<Historico> historicos) {
        this.historicos = historicos;
    }

    @Override
    public HistoricoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListaHistorialBinding itemHistorialBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_lista_historial,parent,false);
        return new HistoricoViewHolder(itemHistorialBinding);
    }

    @Override
    public void onBindViewHolder(HistoricoViewHolder holder, int position) {
        holder.bindConnection(historicos.get(position));
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    public class HistoricoViewHolder extends RecyclerView.ViewHolder {
        private ItemListaHistorialBinding itemHistorial;

        public HistoricoViewHolder(ItemListaHistorialBinding itemHistorial) {
            super(itemHistorial.getRoot());
            this.itemHistorial = itemHistorial;
        }
        public void bindConnection(Historico historico){
            itemHistorial.setHistorial(historico);
        }
    }
}
