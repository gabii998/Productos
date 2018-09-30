package com.example.gabrielascurra.productos.Controlador;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabrielascurra.productos.Datos.HistoricoDAO;
import com.example.gabrielascurra.productos.R;
import com.example.gabrielascurra.productos.modelo.Historico;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistorialFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView textView;
    private FloatingActionButton floatingActionButton;
    private ArrayList<Historico> historial=new ArrayList<>();
    HistoricoAdapter adapter;

    public HistorialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_historial, container, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        floatingActionButton=view.findViewById(R.id.fab_historial);
        recyclerView=view.findViewById(R.id.rv_historial);
        textView=view.findViewById(R.id.txtHistorialVacio);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        poblarRecyclerView(recyclerView,textView);
        if (historial.isEmpty()){
            floatingActionButton.hide();
        }else {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmarEliminacion();
                }
            });
        }
        return view;
    }
    private void poblarRecyclerView(RecyclerView recyclerView,TextView textView){

        HistoricoDAO historicoDAO=new HistoricoDAO(getActivity());
        historial=historicoDAO.obtenerHistorial();
        /*
        historial.add(new Historico("2016-06",3));
        historial.add(new Historico("2016-07",5));*/
        if (!historial.isEmpty()){
            adapter=new HistoricoAdapter(historial);
            recyclerView.setAdapter(adapter);
            textView.setVisibility(View.INVISIBLE);
        }


    }
    private void confirmarEliminacion(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Limpiar lista")
                .setMessage("¿Eliminar todos los elementos?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HistoricoDAO dao=new HistoricoDAO(getActivity());
                        historial.clear();
                        dao.vaciarTabla();
                        adapter.notifyDataSetChanged();
                        textView.setVisibility(View.VISIBLE);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alerta = dialog.create();
        alerta.show();
    }


}
