package com.example.manager.miscontactos.fragment;

import com.example.manager.miscontactos.adapter.MascotaAdaptador;
import com.example.manager.miscontactos.base.Mascota;

import java.util.ArrayList;

/**
 * Created by manager on 08/06/16.
 */
public interface RecyclerView
{
    void generarLinearLayoutVertical();
    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptador(MascotaAdaptador ca);
    //void inicializarListaContactos();
}
