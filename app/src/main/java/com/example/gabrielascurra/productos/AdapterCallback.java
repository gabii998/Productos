package com.example.gabrielascurra.productos;

import android.view.View;

public interface AdapterCallback {
    void onItemClick(View view, int position);
    void onLongItemClick(View view,int position);
}
