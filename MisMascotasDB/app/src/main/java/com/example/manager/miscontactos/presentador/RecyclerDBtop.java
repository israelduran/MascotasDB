package com.example.manager.miscontactos.presentador;

import android.content.Context;

import com.example.manager.miscontactos.base.Mascota;
import com.example.manager.miscontactos.db.ConstructorContactos;
import com.example.manager.miscontactos.fragment.RecyclerView;

import java.util.ArrayList;

/**
 * Created by manager on 08/06/16.
 */
public class RecyclerDBtop implements DBinterface
{
    private RecyclerView rvInterface;
    private Context context;
    private ConstructorContactos cc;
    private ArrayList<Mascota> mascotas;

    public RecyclerDBtop(RecyclerView rvInterface, Context context)
    {
        this.rvInterface = rvInterface;
        this.context = context;
        obtenerContactos();
    }

    @Override
    public void obtenerContactos()
    {
        cc = new ConstructorContactos(context);
        mascotas = cc.obtenerDatosTop5();
        mostrarContactos();
    }

    @Override
    public void mostrarContactos()
    {
        rvInterface.inicializarAdaptador(rvInterface.crearAdaptador(mascotas));
        rvInterface.generarLinearLayoutVertical();
    }
}