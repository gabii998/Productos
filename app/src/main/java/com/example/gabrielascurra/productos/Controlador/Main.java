package com.example.gabrielascurra.productos.Controlador;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.gabrielascurra.productos.R;

public class Main extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                cambiarFragmento(item);
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.nav_pendientes);
    }
    private void cambiarFragmento(MenuItem item){
        Fragment fragmento=null;
        switch (item.getItemId()){
            case R.id.nav_pendientes:
                fragmento = new PendientesFragment();
                break;
            case R.id.nav_historial:
                fragmento = new HistorialFragment();
                break;
            case R.id.nav_configuracion:
                fragmento= new ConfiguracionFragment();
                break;

        }
        if (fragmento != null){
            FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
            transaction.replace(R.id.frameLayout,fragmento);
            transaction.commit();
        }
    }

}
