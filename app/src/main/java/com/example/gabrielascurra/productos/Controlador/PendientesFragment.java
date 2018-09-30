package com.example.gabrielascurra.productos.Controlador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielascurra.productos.AdapterCallback;
import com.example.gabrielascurra.productos.Datos.HistoricoDAO;
import com.example.gabrielascurra.productos.Datos.ProductoDAO;
import com.example.gabrielascurra.productos.R;
import com.example.gabrielascurra.productos.modelo.Historico;
import com.example.gabrielascurra.productos.modelo.Producto;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

public class PendientesFragment extends Fragment implements ActionMode.Callback {
    private RecyclerView lista;
    private TextView listaVacia;
    private FloatingActionButton fab;
    private HashSet<Integer> posiciones=new HashSet<>();
    private ActionMode actionMode;
    private ArrayList<Producto> productos;
    private RecyclerViewAdapter recyclerViewAdapter;

    public PendientesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pendientes, container, false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        fab=view.findViewById(R.id.fab);
        listaVacia=view.findViewById(R.id.listaVacia);
        lista=view.findViewById(R.id.lvProductos);
        lista.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        lista.setLayoutManager(linearLayoutManager);
        lista.setHasFixedSize(true);

        //Se invoca el metodo para poblar la lista
        poblarLista(lista,listaVacia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),Anadir.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void poblarLista(final RecyclerView lista, TextView textView){
        Producto producto=new Producto(getActivity().getApplicationContext());
        productos= producto.getDao().MostrarProductos();


        if (! productos.isEmpty()){
            recyclerViewAdapter=new RecyclerViewAdapter(productos);
            textView.setVisibility(View.INVISIBLE);//Si hay producto en la bd,hace que el textView que indica lo contrario,no se muestre
            lista.setAdapter(recyclerViewAdapter);

            recyclerViewAdapter.setOnItemClickListener(new AdapterCallback() {
                @Override
                public void onItemClick(View view, int position) {
                    if (posiciones.isEmpty()){
                        modificarProducto(productos,position);
                    }else {
                        onLongItemClick(view,position);
                    }
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    if (posiciones.contains(position)){
                        view.setBackgroundColor(Color.WHITE);
                        posiciones.remove(position);
                        actionMode.setTitle("   Seleccionados: "+posiciones.size());
                        if (posiciones.isEmpty()){
                            actionMode.finish();
                        }
                    }else {
                        view.setBackgroundColor(getResources().getColor(R.color.selectedItem));
                        posiciones.add(position);
                        actionMode=startActionMode();
                        actionMode.setTitle("   Seleccionados: "+posiciones.size());
                    }
                }
            });
        }
    }
    public void modificarProducto(ArrayList<Producto> productos,int position){
        Intent intent= new Intent(getActivity().getApplicationContext(),Modificar.class);
        Producto p=productos.get(position);//Se crea un objeto a partir del item seleccionado
        //Y se envian los datos a la activity Modificar
        intent.putExtra("rowId",p.getRowid());
        intent.putExtra("Cantidad",p.getCantidad());
        intent.putExtra("Nombre",p.getNombre());
        intent.putExtra("Precio",p.getPrecio());
        //Inicia la activity modificar
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (actionMode!=null)
        actionMode.finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        poblarLista(lista,listaVacia);
        super.onResume();
    }



    public ActionMode startActionMode(){
        ActionMode actionMode= getActivity().startActionMode(this);
        return actionMode;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.items,menu);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.actionModeStatusBar));
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveList:
                Calendar cal= Calendar.getInstance();
                DateFormat dateFormat=android.text.format.DateFormat.getDateFormat(getActivity());
                String fecha=dateFormat.format(cal.getTime());
                ProductoDAO productoDAO=new ProductoDAO(getActivity());
                Historico historico= new Historico(fecha,productos,productos.size());
                HistoricoDAO historicoDAO=new HistoricoDAO(getActivity());
                historicoDAO.nuevoHistorico(historico);

                for (int i: posiciones) {
                    productoDAO.eliminarProducto(productos.get(i).getRowid());
                }
                posiciones.clear();
                recyclerViewAdapter.notifyDataSetChanged();
                productos.clear();
                poblarLista(lista,listaVacia);
                if (productos.isEmpty())
                    listaVacia.setVisibility(View.VISIBLE);
                actionMode.finish();
        }

        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        mode=null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
